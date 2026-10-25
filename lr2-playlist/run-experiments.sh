#!/bin/sh
# Компілює кожен файл з experiments/ разом з основними класами і показує відповідь javac.
# Ці файли навмисно містять помилки, тому не входять у Maven-збірку.
set -u
cd "$(dirname "$0")"
OUT=$(mktemp -d)
for f in experiments/app/BreakFromOutside.java; do
  echo "=== javac $f ==="
  javac -encoding UTF-8 -d "$OUT" -sourcepath src/main/java "$f" 2>&1 || true
  echo
done
rm -rf "$OUT"
