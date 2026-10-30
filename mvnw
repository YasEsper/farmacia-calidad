#!/bin/sh
# Descarga Maven la primera vez que se usa y lo ejecuta con los argumentos recibidos.
MAVEN_VERSION=3.9.11
WRAPPER_DIR="$HOME/.m2/wrapper"
MAVEN_DIR="$WRAPPER_DIR/apache-maven-$MAVEN_VERSION"

if [ -z "$JAVA_HOME" ]; then
  for jdk in "$HOME"/.jdks/*21* "$HOME"/.jdks/*; do
    if [ -x "$jdk/bin/java" ] || [ -x "$jdk/bin/java.exe" ]; then
      JAVA_HOME="$jdk"
      break
    fi
  done
  export JAVA_HOME
fi

if [ ! -d "$MAVEN_DIR" ]; then
  echo "[mvnw] Descargando Maven $MAVEN_VERSION (solo la primera vez)..."
  mkdir -p "$WRAPPER_DIR"
  curl -fsSL -o "$WRAPPER_DIR/maven.zip" \
    "https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/$MAVEN_VERSION/apache-maven-$MAVEN_VERSION-bin.zip" || exit 1
  (cd "$WRAPPER_DIR" && "$JAVA_HOME/bin/jar" xf maven.zip) || exit 1
  rm -f "$WRAPPER_DIR/maven.zip"
fi

exec sh "$MAVEN_DIR/bin/mvn" "$@"
