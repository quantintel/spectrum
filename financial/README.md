Spectrum / Financial
====================

A Scala port of various open source quantitative and financial software libraries.

##Problem:

Rarely is a non-trivial quantitative or financial computing problem solved with
a single library.  More often then not development teams are required to use 
several libraries and as such go through the effort of integrating the functionality 
while ensuring consistency, transparency and accuracy of the results. 

The challenge with this approach is one of incomplete coverage and diversity
among teams.  It would be unlikely for multiple teams acting independently to 
integrate any arbitrary set of libraries in the same fashion.  Even the same 
team of people over long period of time are likely to adopt different styles
and approaches.  Finally, most teams need to deal with tactical priorities
and as such integrate only what they need at a given point in time, further
impacting consistency and transparency.

##Solution:

One option would be to write a monolithic library with staggering breadth of scope
but doing so would clearly be a daunting task.  Spectrum takes a decidedly
more pragmatic approach.  Spectrum consists of a series of libraries written in Scala
which implement the functionality available in popular open source quantitative and financial 
libraries.  They are not exact ports nor are they forks of the libraries from
which they were inspired.  They do however faithfully implement the algorithms and 
are similar enough to track and subsequently incorporate functionality that 
is added to the originating libraries as they themselves evolve.

An exact port wouldn't achieve the desired goal of providing consistent, transparent,
accurate and seamless integration between the universe of libraries that are included.

A fork of the existing code bases would result in significant design and standards 
variation making the resulting code base challenging to support and maintain.

That said, the goal is for Spectrum to be familiar to those who have used any of
the libraries from which Spectrum is inspired while producing a distribution
that is easily maintained, consistent, transparent and accurate.

##Usability and integration:

Producing a library that is usable in all the contexts that one might wish
to use it is always a trade off.  Despite best efforts of engineers over
the decades there is no single universal platform.  As such some platforms
are integrated more directly then others.  

Spectrum provides programmatic APIs for both Scala and Java.  

Spectrum also provides  web service wrappers making it callable from any 
platform that supports that technology stack.  The web services may be deployed 
locally or remotely depending upon performance and scalability requirements.  

Spectrum also provides a plugin which allows it to integrate with Excel and 
Open Office.  

##Design choices:

The obvious omissions are a native code integration and a .NET platform integration
at the language level.   It is suggested that in most cases invoking Spectrum
via the web services interfaces is the best approach.  For the remaining
cases, where it is unacceptable from a performance perspective to invoke
a web service (either locally or remotely), we would suggest that solutions
such as JnBridge may be a viable alternative.  However, if either of these
cases are not an option then one might simply use the native libraries from
which Spectrum was inspired.  







