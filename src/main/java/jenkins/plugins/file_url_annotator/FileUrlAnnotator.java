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

import hudson.console.ConsoleAnnotator;
import hudson.MarkupText;
import hudson.MarkupText.SubText;

public class FileUrlAnnotator extends ConsoleAnnotator<Object>{
	private static final long serialVersionUID = 1L;
	/**
     * Starts with a word boundary and protocol identifier,
     * Continue until a newline or " are found, or 
     */
    //search for file://nospaces or "file://adsf adsfasdf asdf"
    private static final Pattern URL = Pattern.compile("&quot;file://.*&quot;|" + //File wrapped by quotes that have been html escaped
    		"\'file://.*\'|" + //File wrapped by single quotes
    		"\"file://.*\"|" + //File wrapped by double quotes
    		"file://[^\\s<>]+[^\\s<>,\\.:\"'()\\[\\]=]"); //File without quotes containing no whitespace(\\s) or <>
    													  // ending in any character except the following - whitespace <> , \ . : " ' () [ ] =

	@Override
	public ConsoleAnnotator<Object> annotate(Object context, MarkupText text) {
	  //Search for quoted url or url without spaces
  	  for (SubText t : text.findTokens(URL)) {
  		  //generate the hyperlink for the file removing any wrapping characters
  		  t.href(t.getText().replaceAll("\"|'|&quot;", ""));
	  }
  	  
        return this;
	}    
}
