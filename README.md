# Anchor RSS

[![JitPack](https://img.shields.io/github/v/release/mahendranv/anchor-rss?color=green&label=JitPack&logo=hackthebox&logoColor=white&style=for-the-badge)](https://jitpack.io/#mahendranv/anchor-rss) ![](https://img.shields.io/github/issues/mahendranv/anchor-rss?style=for-the-badge)

WIP 🚧: A simple java-lib backed by Jackson XML parser.

## Usage

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

Call the parser with feed URL.
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
- [ ] Android support

## Releases

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