#
# define compiler and compiler flag variables
#

CLASSPATH = $(PWD)/bin
JFLAGS = -g
JC = javac

all : build-exception build-ihm build-avis build-test

build-avis:
	@mkdir -p $(CLASSPATH)
	$(JC) $(JFLAGS) -d $(CLASSPATH) -classpath $(CLASSPATH) src/avis/*.java

build-exception:
	@mkdir -p $(CLASSPATH)
	$(JC) $(JFLAGS) -d $(CLASSPATH) -classpath $(CLASSPATH) src/exception/*.java

build-ihm:
	@mkdir -p $(CLASSPATH)
	$(JC) $(JFLAGS) -d $(CLASSPATH) -classpath $(CLASSPATH) src/ihm/*.java

build-test:
	@mkdir -p $(CLASSPATH)
	$(JC) $(JFLAGS) -d $(CLASSPATH) -classpath $(CLASSPATH) src/test/*.java

run:
	java -classpath $(CLASSPATH) avis/IHM

test:
	java -Xms256m -classpath $(CLASSPATH) test/TestsSocialNetwork

clean:
	rm -f $(CLASSPATH)/*.class && rm -Rf doc

doc:
	cd src && javadoc -d ../doc avis
