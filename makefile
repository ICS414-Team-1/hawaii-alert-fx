JC = javac
JFLAGS = -g
JAR = javax.json-1.1.jar
.SUFFIXES: .java .class
.java.class:
	$(JC) -cp .:$(JAR) $(JFLAGS) $*.java

CLASSES = \
		HawaiiAlertApp.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

run:
	java -cp .:$(JAR) HawaiiAlertApp

test:
	$(JC) -cp .:$(JAR) $(JFLAGS) Test.java

test_run:
	java -cp .:$(JAR) Test
