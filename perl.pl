@array=("a","b","c");
%hash=("1","mouli","2","perl");
foreach $i(@array)
{
	if($i eq "a")
	{
		redo; 
		print "$i";
	}
	print "$i";
}