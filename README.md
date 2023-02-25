# Anchor RSS

[![JitPack](https://img.shields.io/github/v/release/mahendranv/anchor-rss?color=green&label=JitPack&logo=hackthebox&logoColor=white&style=for-the-badge)](https://jitpack.io/#mahendranv/anchor-rss) ![](https://img.shields.io/github/issues/mahendranv/anchor-rss?style=for-the-badge)

WIP 🚧: A simple java-lib backed by Jackson XML parser.

## Getting Started
Add gradle dependency to the project. Make sure to add the jitpack repo to maven repos list.
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.mahendranv:anchor-rss:{latest-version}'
}
```

### 🚨 Android Only
This library uses Jackson to parse XML. So, you'll end up seeing this error when including the lib in your projects.
```
java.lang.NoClassDefFoundError: Failed resolution of: Ljavax/xml/stream/XMLInputFactory;
```

Because `javax.xml.stream` API is not included in the Android platform. While the javax.xml.stream API is part of the Java SE platform, it's not included in the Android platform by default. As a result, if you try to use the javax.xml.stream API in your Android app, you may run into runtime errors like the NoClassDefFoundError that you're seeing.

To fix this error, you can include the javax.xml.stream API in your app by adding the following dependency to your build.gradle 
```groovy
implementation 'javax.xml.stream:stax-api:1.0-2'
```
This dependency provides the javax.xml.stream API and should resolve the NoClassDefFoundError that you're seeing.


## API call
Call the parser with feed URL. And read through the `AnchorResult` model to consume parsed content.

```java
String feedXmlUrl = "https://anchor.fm/s/e337170/podcast/rss";
AnchorResult result = AnchorParser.parse(strUrl);
switch (result.getStatusCode()) {
    case SUCCESS:
        String podcastTitle = result.getRss().getChannel().getTitle();
        List<Item> episodes = result.getRss().getChannel().getItems();
    break;
    case IO_EXCEPTION:
        // Connectivity error
    break;
    case INVALID_URL:
        // Malformed url
    break;
    case PARSER_FAILURE:
        // Parser failure
    break;
}
```

## TODO
- [x] Github release pipeline
- [x] Gitpack release
- [x] Android support

## Releases
### V_0.2
+ Support for Android 
+ Moved to manual `local_name` collision handling for better flexibility
### V_0.1
Initial setup for model classes and parser. Models are mapped as is from XML without processing.
+ Parsing season and episode fields now

## License
```
MIT License

Copyright (c) 2023 Mahendran

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```