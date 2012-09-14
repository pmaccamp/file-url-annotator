/*
 * The MIT License
 *
 * Copyright (c) 2012 Patrick McKeown
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package jenkins.plugins.file_url_annotator;

import java.util.regex.Pattern;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import hudson.MarkupText;

import org.junit.Test;

/**
 * Unit test for the {@link AnsiColorizer} class.
 */
public class FileUrlAnnotatorTest {

	private static final FileUrlAnnotator ANNOTATOR= new FileUrlAnnotator();
	/**
	 * @throws IOException
	 */
	@Test
	public void testNoMarkup() throws IOException {
		MarkupText text = new MarkupText("file line");
		ANNOTATOR.annotate("",text);
		assertThat(text.toString(true), is("file line"));
	}
	
	@Test
	public void testQuotes() throws IOException {
		MarkupText text = new MarkupText("\"file://this is a test with quotes\"");
		ANNOTATOR.annotate("",text);
		assertThat(text.toString(true), is("<a href='file://this is a test with quotes'>\"file://this is a test with quotes\"</a>"));
	}
	
	@Test
	public void testSingleQuotes() throws IOException {
		MarkupText text = new MarkupText("'file://this is a test with single quotes'");
		ANNOTATOR.annotate("",text);
		assertThat(text.toString(true), is("<a href='file://this is a test with single quotes'>'file://this is a test with single quotes'</a>"));
	}
	
	@Test
	public void testHtmlEscaping() throws IOException {
		MarkupText text = new MarkupText("&quot;file://this is a html escaping test&quot;");
		ANNOTATOR.annotate("",text);
		assertThat(text.toString(true), is("<a href='file://this is a html escaping test'>&amp;quot;file://this is a html escaping test&amp;quot;</a>"));
	}
	
	@Test
	public void testNoWrapping() throws IOException {
		MarkupText text = new MarkupText("file://thisisatestwithoutwrapping");
		ANNOTATOR.annotate("",text);
		assertThat(text.toString(true), is("<a href='file://thisisatestwithoutwrapping'>file://thisisatestwithoutwrapping</a>"));
	}
}