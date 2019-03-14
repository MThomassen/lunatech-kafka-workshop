AVRO Intro
---

## Download AVRO Tools
```bash
curl -O -J http://central.maven.org/maven2/org/apache/avro/avro-tools/1.8.2/avro-tools-1.8.2.jar
```

### Generate AVRO Serializer/Deserializer Java code
```bash
java -jar ./avro-tools-1.8.2.jar compile schema user.avsc .
```