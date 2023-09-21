# remove existing module files
find . -name "*.iml" -exec rm -- {} +
# crete new module files
for p in (find . -regex '.*/Java/[0-9][0-9]-[^/]*$' -type d);
	boil use ijm $p
end
# add all modules to java-modules.txt
find . -name "*.iml" > java-modules.txt
