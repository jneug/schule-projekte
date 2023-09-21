for p in (find -s . -regex '.*/Java/[^/]*$' -type d);
	set n (basename $p)
	echo "{"
	echo "  \"name\": \"$n\","
	echo "  \"path\": \"$p\""
	echo "},"
end
