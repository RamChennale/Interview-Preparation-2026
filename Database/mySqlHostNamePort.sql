-- password: root
Enter password: ****
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 9
Server version: 26.7.0 MySQL Community Server - GPL

Copyright (c) 2000, 2026, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> SHOW VARIABLES WHERE Variable_name IN ('hostname','port');
+---------------+-----------------+
| Variable_name | Value           |
+---------------+-----------------+
| hostname      | DESKTOP-3QSSNNK |
| port          | 3306            |
+---------------+-----------------+
2 rows in set (1.439 sec)



mysql> SELECT CURRENT_USER();
+----------------+
| CURRENT_USER() |
+----------------+
| root@localhost |
+----------------+
1 row in set (0.044 sec)

mysql>

