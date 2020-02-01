DROP TABLE IF EXISTS Manager;

CREATE TABLE Manager(
    ID NUMBER,
    SERVICE_NAME VARCHAR2(256),
    MANAGER_LOGIN VARCHAR2(256),
    SERVICE_TIME_START DATE,
    SERVICE_TIME_END DATE,
    PRIMARY KEY(id)
);