SOLID

S : All the classes are doing only one thing and doing it very well, thus it follows the Single Responsibility Principle really well

O : The classes are open for extension and closed for extension, this is followed by coding to the interface, so there is a well defined 
contract so that the functionality can be extended but not at all get changed itself. For example, if tomorrow the Cache class wants to use 
any network based storage then it can easily plug that by just implementing a new instance of the CacheStorage interface, thus it's open for 
an extension, but it's not open for any modification itself. 

L : 

I : Interface Segragation Principle states that the clients shouldn't be forced to depend on the interfaces that they don't use. It's being followed 
nicely in this example

D : Dependency Injection is followed nicely in the Cache class where in we are injecting the dependency of the EvictionPolicy and CacheStorage and it's 
being wired in the constructor. 

** A good design should always have immutable things, since it helps us in keeping the objects 'secure' when they aren't required to be changed once they 
are initialised

