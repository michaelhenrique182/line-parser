line-parser
===========

An `mmap()` based line parser for cases when:

 * the start position in the file of a line is required
 * the length in bytes of a line is required
 * only a few character of every line is required

In these cases it can theoretically be more efficient than `BufferedReader` because:

 * the copy operations of buffered IO are avoided
 * the allocation and resizing of an intermediate StringBuffer is avoided
 * the allocation of the final String is avoided, only the required substrings
   are allocated

The performance way still be slower than a than `BufferedReader` based approach but it should consume much less memory bandwidth and produce only a fraction of the garbage.

Limitations
-----------

 * doesn't handle file encodings with a BOM

Misc
----
 * all methods have been verified with [jitwatch-jarscan-maven-plugin](https://github.com/ferstl/jitwatch-jarscan-maven-plugin) to be below 325 bytecode instructions and should therefore inline
 * the main parsing loop is likely to profit from [on-stack replacement (OSR)](http://openjdk.java.net/groups/hotspot/docs/HotSpotGlossary.html#onStackReplacement)

