javac *.java

mkdir -p testResultHoward

set test_dict = "sowpods.txt"
set test_in = "testFiles/aestnlr.in"
set golden = "testFiles/aestnlr.out"
set test_out = "testResultHoward/aestnlr.howard.out"
java WordFinder $test_dict < $test_in > $test_out
echo "diff between" $golden $test_out
diff $golden $test_out

set test_in = "testFiles/test1.in"
set golden = "testFiles/test1.out"
set test_out = "testResultHoward/test1.howard.out"
java WordFinder $test_dict < $test_in > $test_out
echo "diff between" $golden $test_out
diff $golden $test_out

set test_in = "testFiles/test2.in"
set golden = "testFiles/test2.out"
set test_out = "testResultHoward/test2.howard.out"
java WordFinder $test_dict < $test_in > $test_out
echo "diff between" $golden $test_out
diff $golden $test_out

set test_dict = "testFiles/tinyDictionary.txt"
set test_in = "testFiles/tiny.in"
set golden = "testFiles/tiny.out"
set test_out = "testResultHoward/tiny.howard.out"
java WordFinder $test_dict < $test_in > $test_out
echo "diff between" $golden $test_out
diff $golden $test_out

set test_dict = "testFiles/tinyDictionaryUpper.txt"
set test_in = "testFiles/tinyUpper.in"
set golden = "testFiles/tinyUpper.out"
set test_out = "testResultHoward/tinyUpper.howard.out"
java WordFinder $test_dict < $test_in > $test_out
echo "diff between" $golden $test_out
diff $golden $test_out

