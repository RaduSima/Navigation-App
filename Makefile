run: build
	java com/company/Main

build:
	javac ./com/company/*.java
	
clean:
	rm ./com/company/*.class
