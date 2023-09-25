CREATE KEYSPACE IF NOT EXISTS helpdesk
    WITH replication = {
        'class':'SimpleStrategy',
        'replication_factor':1
    };