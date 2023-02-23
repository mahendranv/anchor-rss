# Anchor RSS

WIP 🚧: A simple java-lib backed by Jackson XML parser.

## Usage

```java
AnchorResult result=AnchorParser.parse(feedXmlUrl);
```

## TODO
- [ ] Github release pipeline
- [ ] Gitpack release

## Releases

### V_0.1
Initial setup for model classes and parser. Models are mapped as is from XML without processing.
+ Parsing season and episode fields now