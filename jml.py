#!/usr/bin/env python3
import os
import argparse
import shutil

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
parser.add_argument('-f', '--format', dest="format", action="store", default="{name}_{ver:02d}",
                    help='format for version names')
parser.add_argument('-i', '--include', dest="include", action="extend", nargs="+",
										default=['java'],
                    help='list of file extensions to parse for jml comments')
parser.add_argument('-e', '--exclude', dest="exclude", action="extend", nargs="+",
										default=['class','ctxt'],
                    help='list of file extensions to exclude from processing')
parser.add_argument('-v', '--versions', dest="versions", action="append", type=str,
                    help='list of versions to process')
parser.add_argument('--preset', dest="preset",
										choices=['xml', 'de', 'de-xml'],
                    help='use a preset tag style')
parser.add_argument('--project-root', dest="root", action='store',
                    help='if set to a prefix of IN, the folder structure in OUT will reflect the structure of the project root. For IN=/projects/foo/bar/my-project OUT=/projects/out project-root=/projects the resulting versions will be written to /projects/out/foo/bar')

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

def test_version(version1, version2):
	"""	Compares a version with a version string and checks if the first
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

def create_version(version, args):
	if version == 0:
		ver_name = args.name
	else:
		ver_name = args.format.format(name=args.name, ver=version)
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
				with open(fullpath, 'r') as inf:
					with open(fulloutpath, 'w') as outf:
						skip = False
						line = inf.readline()
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
							line = inf.readline()
			else:
				shutil.copy(fullpath, fulloutpath)

def create_ml(args):
	versions = set()

	ver_name = args.format.format(name=args.name, ver=args.ml_suffix)
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
				with open(fullpath, 'r') as inf:
					with open(fulloutpath, 'w') as outf:
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
							line = inf.readline()
			else:
				shutil.copy(fullpath, fulloutpath)

	if not versions:
		versions.add(0)
	return versions

if os.path.isdir(args.indir):
	if not args.name:
		args.name = os.path.basename(args.indir)

	if args.root and os.path.commonprefix([args.root, args.indir]) == args.root:
		args.outdir = os.path.dirname(os.path.join(args.outdir, args.indir[len(args.root)+1:]))

	versions = create_ml(args)
	if not args.versions:
		args.versions = versions
	for ver in versions:
		if any(test_version(ver, v) for v in args.versions):
			create_version(ver, args)
else:
	print(f'{args.indir} does not exist')
