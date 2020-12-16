# Clausewitz-Java Core
Core for Clausewitz engine serialization to Java.\
Allows deserializing Clausewitz text files into objects and serializing those objects into Clausewitz text files.\
Without plugins this framework does nothing. Since for it to work a plugin which at least dictates managed files has to be created.\
Plugins allow parser to create less generic objects using definitions. Parser will attempt to use the most fitting definition when parsing. Later using polymorphism you can obtain instances of your custom object.
//todo: needs elaboration 

#IMPORTANT
Not finished. The code isn't properly covered by tests, not refined, and some features are missing:
1) Maps inside arrays will not be parsed, since that case isn't handled yet. This WILL cause loss of data.
2) Maven source-code generation plugin isn't started. This means creating plugins is slightly more complicated than it could be.
3) Scripting is not supported.
4) Hell, this readme isn't finished.

## Creating plugins:
Currently, this framework isn't finished. This means creating plugins for it is not as comfortable as i want it to be.\
In the future most of the plugin creation will be handled by source code generation, to maintain as much uniformity as possible and to save your time.\

##### How to create plugins in the current version?
Prefer waiting for the finished, ~~non rushed~~ version. To create plugins for this version a lot of boiler-plate code needs to be created.
1) Provide a ClausewitzFileProvider implementation.
Only files that are in the plugins scope should be provided.
2) Create ClausewitzObjectDefinition for each clausewitz map you need.
Parser uses definitions to build any object you need from the parsed map (As long as it extends ClausewitzObject abstract class).
2) Provide a ClausewitzObjectDefinitionsProvider implementation. It will provide the created definitions to parser.


# Credits
To generate source code this code will use JavaPoet: https://github.com/square/javapoet \
ANTLR4 grammar used in this project: https://github.com/Realiante/Clausewitz-Antlr
