package com.github.marschall.lineparser;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.US_ASCII;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Reads a line into a {@link CharSequence}.
 */
interface LineReader {

  /**
   * Reads a line.
   *
   * <p>This is a view in a mutable buffer that is reused. Any content that
   * is used after the callback has to be copied with
   * {@link CharSequence#toString()} ideally calling
   * {@link CharSequence#subSequence(int, int)} first.</p>
   *
   * @param buffer the line in bytes
   * @return the line in characters
   */
  CharSequence readLine(ByteBuffer buffer);


  /**
   * Creates a new instance for the given character set.
   *
   * @param charset the character set
   * @return the line reader instance for the given character set
   */
  static LineReader forCharset(Charset charset) {
    if (isLatin1Compatible(charset)) {
      return new NonDecodingLineReader();
    } else {
      return new DecodingLineReader(charset);
    }
  }



  static boolean isLatin1Compatible(Charset charset) {
    return US_ASCII.equals(charset) || ISO_8859_1.equals(charset);
  }

}
