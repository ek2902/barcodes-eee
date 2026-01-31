# Barcodes

## About

_(TODO: insert a description of the project here!)_

### Authors

*  El Houston
*  Emory Chien
*  Eva Kapoor

### Resources

*   _(TODO: list your resources here!)_
*   ...

## Write-up

### Part 2.1: Old Hat, New Hat

    a.
    string-append/strcat (Scheme/C)
    to-uppercase/to-lowercase (Scheme/C)
    substring (Scheme)
    remove whitespace (C)
    index of (C)
    strcmp (C)
    strncmp(C)
    strcpy (C)
    + operator (Python)

    b.
    append: 
        String concat(String str): Concatenates the specified string to the end of this string.
    to-upper: 
        String toUpperCase(): Converts all of the characters in this String to upper case using the rules of the default locale.
    substring: 
        String substring(int beginIndex, int endIndex): Returns a string that is a substring of this string.
    remove whitespace: 
        String trim(): Returns a string whose value is this string, with any leading and trailing whitespace removed.
    index of: 
        char charAt(int index): Returns the char value at the specified index
    strcmp: 
        int compareTo(String anotherString): Compares two strings lexicographically.
    strncmp: 
        public boolean startsWith(String prefix, int toffset): Tests if the substring of this string beginning at the specified index starts with the specified prefix.
    strcpy: 
        String(String original): Initializes a newly created String object so that it represents the same sequence of characters as the argument; in other words, the newly created string is a copy of the argument string.
    + operator: 
        Also works in Java.
        

### Part 2.2: Iceberg, Right Ahead!
1. The code doesn't work as expected! The first two strings are equal, but none of the rest are. 
2. The equals method worked on all comparisons, no matter which one it was called on. To compare two strings for equality, we must use the equals method in the string class. 
3. The code does not work as expected because the "==" operator checks the equality of the pointers to the objects, but not the  contents of the objects. Therefore for the B and C string checks, the equality operator checks that the pointers are different and subsequently returns a boolean false.
4. Whenever you intitialize a string via string literal (or string-valued constant expression), then it is added to a pool maintained by the string class. Then, if you attempt to initialize a new string with a string literal (or string-valued constant expression), the String class will return a pointer to the same object previously made and stored in this pool. Therefore, when the first two string objects are created, the second, s2 is actually just a different pointer to the same object as s1. Therefore the pointers are the same and the equality operator returns a boolean true.
