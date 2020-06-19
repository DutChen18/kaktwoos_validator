javac -d class $(find src -name "*.java")
jar -cfe kaktwoos.jar Main -C class .