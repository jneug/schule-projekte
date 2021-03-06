#!/usr/bin/env python3
import os
import argparse
import shutil
import sys
import zipfile

parser = argparse.ArgumentParser(description='Compile java source files with special tags into a version with new content')
parser.add_argument('indir', metavar='IN',
                    help='project directory containting the source files')
parser.add_argument('outdir', metavar='OUT',
                    help='output folder')
parser.add_argument('-n', '--name', dest="name", action="store",
                    help='name for the project. default is the basename of IN')
parser.add_argument('-c', '--clear', dest="clear", action="store_true",
                    help='delete OUT before creating project versions')
parser.add_argument('-to', '--tag-open', dest="tag_open", action="store", default='jml*',
                    help='opening tag for jml comments')
parser.add_argument('-tc', '--tag-close', dest="tag_close", action="store", default='*jml',
                    help='closeing tag for jml comments')
parser.add_argument('-mlo', '--ml-open', dest="ml_open", action="store",
                    help='opening tag for jml comments')
parser.add_argument('-mlc', '--ml-close', dest="ml_close", action="store",
                    help='closeing tag for jml solution comments')
parser.add_argument('-mls', '--ml-suffix', dest="ml_suffix", action="store", default="ML",
                    help='suffix for the solution version')
parser.add_argument('-vs', '--ver-sep', dest="sep", action="store", default="_",
                    help='separator between project name and version number')
parser.add_argument('-i', '--include', dest="include", action="extend", nargs="+",
                                        default=['java'],
                    help='list of file extensions to parse for jml comments')
parser.add_argument('-e', '--exclude', dest="exclude", action="extend", nargs="+",
                                        default=['class','ctxt','Thumbs.db','.DS_Store'],
                    help='list of file extensions to exclude from processing')
parser.add_argument('-v', '--versions', dest="versions", action="extend", type=str,
                    help='list of versions to process')
parser.add_argument('--preset', dest="preset",
                                        choices=['xml', 'de', 'de-xml'],
                    help='use a preset tag style')
parser.add_argument('--project-root', dest="root", action='store',
                    help='if set to a prefix of IN, the folder structure in OUT will reflect the structure of the project root. For IN=/projects/foo/bar/my-project OUT=/projects/out project-root=/projects the resulting versions will be written to /projects/out/foo/bar')
parser.add_argument('--keep-empty', dest="keep_empty", action='store_true',
                    help='By default empty files are omitted. This will keep those files in the output.')
parser.add_argument('--encoding', dest="encoding", action='store', default="utf-8",
                    help='Set the encoding for project files Default: utf-8.')
parser.add_argument('-z', '--zip', dest="create_zip", action='store_true', default=False,
                    help='Create additional zip files of the output folders.')
parser.add_argument('--no-ml', dest="delete_ml", action='store_true',
                    help='Skip generating a ml version of the project. Note that the ml version is always generated, but this will delete the folder afterwards.')

parser.add_argument('--debug', dest="debug", action='store_true',
                    help='Enable more verbose debug output.')

args = parser.parse_args()

if args.preset:
    if args.preset == 'xml':
        args.tag_open = '<jml>'
        args.tag_close = '</jml>'
    elif args.preset == 'de':
        args.tag_open = 'aufg*'
        args.tag_close = '*aufg'
        args.ml_open = 'ml*'
        args.ml_close = '*ml'
    elif args.preset == 'de-xml':
        args.tag_open = '<aufg>'
        args.tag_close = '</aufg>'
        args.ml_open = '<ml>'
        args.ml_close = '</ml>'

if args.tag_open == args.tag_close:
    print("You can't use the same tag for opening and closeing jml comments.")
    print("Please set distinct tags. e.g. @jml and lmj@")
    quit()

if args.ml_open and args.ml_open == args.ml_close:
    print("You can't use the same tag for opening and closeing jml solution comments.")
    print("Please set distinct tags. e.g. @jml and lmj@")
    quit()

if not args.ml_open:
    args.ml_open = args.tag_open
if not args.ml_close:
    args.ml_close = args.tag_close

def debug(msg):
    if args.debug:
        print(msg)

def test_version(version1, version2):
    """ Compares a version with a version string and checks if the first
    is in the range defined by the second. The second version can be
    prefixed by one of =, <, >, >=, <= or != to compare with a range of
    versions.
    """
    version1 = int(version1)
    version2 = str(version2)

    ver1 = version1
    ver2 = int(version2.lstrip('<>=!'))
    op   = version2.rstrip('0123456789')

    if len(op) == 0 or op == '=':
        return ver1 == ver2
    else:
        return eval(f'{ver1}{op}{ver2}')

def create_zip(dir, args):
    with zipfile.ZipFile(f'{dir}.zip', 'w', zipfile.ZIP_DEFLATED) as zipf:
        for root, dirs, files in os.walk(dir):
            for file in files:
                filepath = os.path.join(root, file)
                relpath = os.path.relpath(filepath, start=dir)
                zipf.write(filepath, arcname=relpath)

def create_version(version, args):
    if version == 0:
        ver_name = args.name
    else:
        ver_name = f'{args.name}{args.sep}{version}'
    outdir = os.path.join(args.outdir, ver_name)

    # prepare output folders
    if os.path.isdir(outdir) and args.clear:
        shutil.rmtree(outdir)
    if not os.path.isdir(outdir):
        os.makedirs(outdir)

    for root, dirs, files in os.walk(args.indir):
        subpath = root[len(args.indir)+1:]
        outroot = os.path.join(outdir, subpath)

        os.makedirs(outroot, exist_ok=True)

        for file in files:
            fullpath = os.path.join(root,file)
            fulloutpath = os.path.join(outroot, file)

            _, ext = os.path.splitext(file)
            ext = ext[1:]

            if ext in args.exclude:
                continue
            elif ext in args.include:
                is_empty = True
                with open(fullpath, 'r', encoding=args.encoding) as inf:
                    with open(fulloutpath, 'w', encoding=args.encoding) as outf:
                        skip = False
                        line = inf.readline()
                        #if args.encoding != 'utf-8':
                        #   line = line.decode(args.encoding).encode()
                        while line:
                            lline = line.lstrip()
                            if lline.startswith(f'//{args.ml_close}'):
                                skip = False
                            elif lline.startswith(f'{args.tag_close}*/'):
                                skip = False
                            elif lline.startswith(f'//{args.ml_open}'):
                                skip = True
                            elif lline.startswith(f'/*{args.tag_open}'):
                                parts = lline.split()
                                if len(parts) > 1:
                                    skip = not test_version(version, parts[1])
                                else:
                                    skip = False
                            elif skip:
                                pass
                            else:
                                outf.write(line)
                                is_empty = False
                            line = inf.readline()
                if is_empty and not args.keep_empty:
                    os.remove(fulloutpath)
            else:
                shutil.copy(fullpath, fulloutpath)

    if args.create_zip:
        create_zip(outdir, args)

def create_ml(args):
    versions = set()

    ver_name = f'{args.name}{args.sep}{args.ml_suffix}'
    outdir = os.path.join(args.outdir, ver_name)

    # prepare output folders
    if os.path.isdir(outdir) and args.clear:
        shutil.rmtree(outdir)
    if not os.path.isdir(outdir):
        os.makedirs(outdir)

    for root, dirs, files in os.walk(args.indir):
        subpath = root[len(args.indir)+1:]
        outroot = os.path.join(outdir, subpath)

        os.makedirs(outroot, exist_ok=True)

        for file in files:
            fullpath = os.path.join(root,file)
            fulloutpath = os.path.join(outroot, file)

            _, ext = os.path.splitext(file)
            ext = ext[1:]

            debug(f'working on {fullpath}')

            if ext in args.exclude:
                continue
            elif ext in args.include:
                is_empty = True
                with open(fullpath, 'r', encoding=args.encoding) as inf:
                    with open(fulloutpath, 'w', encoding=args.encoding) as outf:
                        skip = False
                        line = inf.readline()
                        while line:
                            lline = line.lstrip()
                            if lline.startswith(f'//{args.ml_close}'):
                                pass
                            elif lline.startswith(f'//{args.ml_open}'):
                                pass
                            elif lline.startswith(f'{args.tag_close}*/'):
                                skip = False
                            elif lline.startswith(f'/*{args.tag_open}'):
                                parts = lline.split()
                                if len(parts) > 1:
                                    ver = parts[1].lstrip('<>!=')
                                    versions.add(ver)
                                skip = True
                            elif skip:
                                pass
                            else:
                                outf.write(line)
                                is_empty = False
                            line = inf.readline()
                if is_empty and not args.keep_empty:
                    os.remove(fulloutpath)
            else:
                shutil.copy(fullpath, fulloutpath)

    if args.delete_ml:
        shutil.rmtree(outdir)
    elif args.create_zip:
        create_zip(outdir, args)

    if not versions:
        versions.add(0)
    return versions

if os.path.isdir(args.indir):
    if not args.name:
        args.name = os.path.basename(args.indir)

    if args.root and os.path.commonprefix([args.root, args.indir]) == args.root:
        args.outdir = os.path.dirname(os.path.join(args.outdir, args.indir[len(args.root)+1:]))

    debug(f'Compiling project <{args.name}>\n  from <{args.indir}>\n  to <{args.outdir}>')
    debug('Creating ML version:')
    versions = create_ml(args)

    if not args.versions:
        args.versions = versions
    for ver in versions:
        if any(test_version(ver, v) for v in args.versions):
            debug(f'Creating version {ver}:')
            create_version(ver, args)
else:
    print(f'{args.indir} does not exist')
