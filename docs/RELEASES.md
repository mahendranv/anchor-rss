# Releases

## Next Release
-  Added `explicit` flag
### Model changes
#### Removed
  - AnchorResult # rss
#### Added
  - AnchorResult # channel
  - Channel # explicit
  - Channel # category
  - Channel # feedUrl
  - Item # explicit
  - Item # durationInSeconds
  - Item # Enclosure (url, length, type)

---
## V_0.2
+ Support for Android
+ Moved to manual `local_name` collision handling for better flexibility
## V_0.1
Initial setup for model classes and parser. Models are mapped as is from XML without processing.
+ Parsing season and episode fields now
