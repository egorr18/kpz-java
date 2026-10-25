# Конструювання програмного забезпечення Java — практичні та лабораторні роботи

Виконав: **Король Єгор Ігорович**, група ТЦР-31, ДУІКТ (№ у списку — 11).
Варіанти: ЛР1 — варіант 5 (`(11 − 1) mod 6 + 1`), ПР2–ЛР4 — варіант 1 (`11 mod 5`).

| Робота | Тема | Тека | Тег |
|---|---|---|---|
| ПР1 | Maven-проєкт на JDK 21, зовнішня залежність, байт-код | [`pr1-maven-basics`](pr1-maven-basics) | `pr1` |
| ЛР1 | Консольний конвертер швидкості, fat-jar | [`lr1-unit-converter`](lr1-unit-converter) | `lr1` |
| ПР2 | Модифікатори доступу, конструктори, `this`, `static`, `final` (Гравець) | [`pr2-oop-basics`](pr2-oop-basics) | `pr2` |
| ЛР2 | Плейлист і треки з контролем стану | [`lr2-playlist`](lr2-playlist) | `lr2` |
| ПР3 | Успадкування, `@Override`, `super` (Транспорт) | [`pr3-inheritance`](pr3-inheritance) | `pr3` |
| ЛР3 | Поліморфна обробка, LSP (Співробітники) | [`lr3-payroll`](lr3-payroll) | `lr3` |
| ПЗ4 | Абстрактні класи, інтерфейси, DI через конструктор (Пристрої) | [`pz4-abstractions`](pz4-abstractions) | `pz4` |
| ЛР4 | Розширюваний сервіс доставки, OCP/ISP/DIP | [`lr4-delivery`](lr4-delivery) | `lr4` |
| СР1 | Spring Boot REST API камери: OpenFeign, DTO, Swagger | [`camera-api`](camera-api) (гілка `sr1`) | `sr1` |
| СР2 | H2 + Spring Data JPA для Camera API | [`camera-api`](camera-api) | `sr2` |

Логи збирання, виводи програм, повідомлення компілятора та діаграми — у теці [`docs`](docs).

## Збирання

Потрібні JDK 21 і Maven 3.9+ (або Maven, вбудований в IntelliJ IDEA):

```bash
cd lr1-unit-converter
mvn clean package
java -jar target/converter.jar
```

Навмисно некомпільовані приклади (спроби порушити доступ, перезаписати `final` тощо) лежать у
`experiments/` відповідних робіт і перевіряються скриптом `run-experiments.sh`.
