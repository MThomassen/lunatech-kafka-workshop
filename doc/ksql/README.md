KSQL Intro
---

### Start KSQL Server
```bash
docker-compose up
```

### Run KSQL CLI Tools
```bash
docker run -it --network host confluentinc/cp-ksql-cli http://localhost:8088
```


```sql
CREATE STREAM stockTicker (symbol VARCHAR, value DOUBLE) WITH (KAFKA_TOPIC='stock-ticker', VALUE_FORMAT='JSON');
```