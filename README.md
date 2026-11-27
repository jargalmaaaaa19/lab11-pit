# Lab 11 — Mutation Testing (PIT)

- **Нэр:** З.Жаргалмаа
- **Оюутны код:** B232270005
- **Орчин (`results/env.txt`):**

openjdk version "17.0.21.0.3" 2026-10-24
OpenJDK Runtime Environment (build 17.0.21.0.3+1-1-24.04.1-Ubuntu)
OpenJDK 64-Bit Server VM (build 17.0.21.0.3+1-1-24.04.1-Ubuntu, mixed mode, sharing)
[1mApache Maven 3.8.7[m
Maven home: /usr/share/maven
Java version: 17.0.21.0.3, vendor: Ubuntu, runtime: /usr/lib/jvm/java-17-openjdk-amd64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "6.6.87.2-microsoft-standard-wsl2", arch: "amd64", family: "unix"

## А. Амьд мутантуудын (`SURVIVED`) ангилал

| # | Мөр / Мутант | Ангилал | Үндэслэл |
|---|----------------|----------|----------|
| 1 | 20-р мөр: `changed conditional boundary` (`score < 0` / `score > 100`) | Тест дутуу | `score = 0` болон `score = 100` захын утгуудад алдаа гарахгүйг шалгах нэгж тест дутуу байна. |
| 2 | 23-р мөр: `replaced return value with ""` (`return "A"`) | Тест дутуу | `score >= 90` үед яг `"A"` буцааж байгааг шалгах тест дутуу байна. |
| 3 | 23-р мөр: `changed conditional boundary` / `removed conditional` (`score >= 90`) | Тест дутуу | 90 онооны босго дээрх болон `>= 90` нөхцөлийн үнэн/худал салааг ялгаж шалгах тест дутуу байна. |
| 4 | 24-р мөр: `changed conditional boundary` / `removed conditional` / `replaced return value with ""` (`score >= 80`, `return "B"`) | Тест дутуу | 80–89.99 онооны зааг болон яг `"B"` үнэлгээ буцахыг шалгах нэгж тест дутуу байна. |
| 5 | 25-р мөр: `changed conditional boundary` / `removed conditional` / `replaced return value with ""` (`score >= 70`, `return "C"`) | Тест дутуу | 70–79.99 онооны зааг болон яг `"C"` үнэлгээ буцахыг шалгах нэгж тест дутуу байна. |
| 6 | 26-р мөр: `changed conditional boundary` / `removed conditional` / `replaced return value with ""` (`score >= 60`, `return "D"`) | Тест дутуу | 60–69.99 онооны зааг болон яг `"D"` үнэлгээ буцахыг шалгах нэгж тест дутуу байна. |
| 7 | 27-р мөр: `replaced return value with ""` (`return "F"`) | Тест дутуу | 60-аас доош оноонд яг `"F"` буцааж байгааг шалгах нэгж тест дутуу байна. |
| 8 | 36-р мөр: `Changed switch default to be first case` | Тест дутуу | Энэ мутант `"A"` дүнгийн case-ийг default руу чиглүүлж `total += 4`-ийг алгасуулдаг тул голч өөрчлөгдөнө; `gpa({95}) == 4.0` гэсэн яг утгын тест дутуу байна. |
| 9 | 38-р мөр: `Replaced double addition with subtraction` (`total += 3`) | Тест дутуу | `"B"` дүнтэй оролт ороход нийлбэр зөв нэмэгдэж байгааг хянах тодорхой утгатай тест дутуу байна. |
| 10 | 40-р мөр: `Replaced double addition with subtraction` (`total += 1`) | Тест дутуу | `"D"` дүнтэй оролт ороход нийлбэр зөв нэмэгдэж байгааг хянах тодорхой утгатай тест дутуу байна. |
| 11 | 44-р мөр: `replaced double return with 0.0d` (`return total / scores.length`) | Тест дутуу | Голч дүнгийн бодит тоон утгыг шалгах тест дутуу байна. |
| 12 | 48-р мөр: амьд үлдсэн 3 мутант (`changed conditional boundary` 2 ширхэг, `removed conditional ... true` 1 ширхэг — `gpa < 0`, `gpa > 4`, `credits < 0` нөхцөлүүд) | Тест дутуу | `gpa = 0`, `gpa = 4`, `credits = 0` захын утгууд болон эдгээр нөхцөлийн салааг ялгаж шалгах нэгж тест дутуу байна. |
| 13 | 51-р мөр: `removed conditional` (`replaced equality check with false` ба `true` хоёулаа, `if (hasViolation) return 0`) | Тест дутуу | Сахилгын зөрчилтэй ба зөрчилгүй үеийн тэтгэлгийн утгыг бүрэн шалгах нэгж тест дутуу байна. |
| 14 | 52-р мөр: `changed conditional boundary` / `removed conditional` / `replaced int return with 0` (`gpa >= 3.8 && credits >= 15`, `return 100`) | Тест дутуу | Голч `>= 3.8` ба кредит `>= 15` зааг болон `100` тэтгэлэг олгох тохиолдлыг шалгах тест дутуу байна. |
| 15 | 53-р мөр: `changed conditional boundary` / `removed conditional` / `replaced int return with 0` (`gpa >= 3.4 && credits >= 12`, `return 70`) | Тест дутуу | Голч `>= 3.4` ба кредит `>= 12` зааг болон `70` тэтгэлэг олгох тохиолдлыг шалгах тест дутуу байна. |
| 16 | 54-р мөр: `changed conditional boundary` / `removed conditional` / `replaced int return with 0` (`gpa >= 3.0`, `return 50`) | Тест дутуу | Голч `>= 3.0` зааг болон `50` тэтгэлэг олгох тохиолдлыг шалгах тест дутуу байна. |
