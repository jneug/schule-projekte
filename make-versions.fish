# Generate versions for all java projects
set VERSIONS_PATH "/Users/jneug/Projekte/Schule/schule-versionen"
echo "Generating Versions in $VERSIONS_PATH"
for p in (find (pwd) -regex '.*/Java/[0-9][0-9]-[^/]*$' -type d);
	echo "    Generating versions for" (basename $p)
	python3 ./jml.py --clear -n (basename $p) --exclude "iml" --preset de "$p" "$VERSIONS_PATH" --project-root "/Users/jneug/Projekte/Schule/schule-projekte"
end
echo "  All done"
