File Url Annotator Plugin
=======

This plugin is intended to provide file:// hyperlinks in jenkins console output.  Its purpose was to provide a way to view log files easily in the console output (and in any email reports).

Installation
=======
Add file-url-annotator.hpi to jenkins/plugins and restart.  File:// annotation will occur on all jobs without any configuration, there is currently no per job toggle.

Examples
=======
The following ruby code

```ruby
puts "Here's a basic example - file://example.txt"
puts "Here's an example using single quotes - 'file://example.example.com/C$/example.txt'"
puts "Here's an example with spaces and double quotes - \"file://example with spaces/C$/example.txt\""
puts "Here's an example with escaped html quotes - &quot;file://example.example.com/C$/example.txt&quot;"
```

generates

![examples](https://github.com/pmaccamp/file-url-annotator/raw/master/images/fileurl-consoleoutput.PNG "Examples")

Integration with Chrome and Firefox
=======

Using the following browser locallinks plugin/add-on<br>
<a href="https://chrome.google.com/webstore/detail/jllpkdkcdjndhggodimiphkghogcpida">Chrome</a><br>
<a href="https://addons.mozilla.org/en-US/firefox/addon/locallink/">Firefox</a><br> 
IE - built in<br>
The file links can then be accessed via a single click.  Without the plugins, the security models of Chrome and Firefox will cause nothing to happen when you click a file:// link.