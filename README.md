Spectrum
========


A series of capability distributions packaged as libraries.

    The initial focus will be in the Financial/Quantitative area


## Wiki:
    
[Spectrum Wiki](https://github.com/quantintel/spectrum/wiki)
    
## Concept:
    
What is a "Capability Distribution"?  

The open source community is flush with frameworks and libraries.  New frameworks and 
libraries replace older ones all of the time.  While vast improvements have resulted 
in terms of technical capabilities it can also be said that the ball isn't really being moved 
forward.

For example, while libraries dedicated to communications, infrastructure, and
web application development continue to improve the high level business capabilities available
in the open source community evolve at a much slower pace.  

This is not surprising when you consider that software engineers are trained to
become experts in communications, infrastructure and the like.  It does however 
create a gap that is normally filled by teams of corporate developers who spend centuries
worth of collective effort integrating a field of disparate libraries and frameworks and
in so doing duplicate what many other teams have already done.   Add to this the fact that
soon after one integrates a set of libraries new versions become available and will continuously
become available requiring constant maintenance.

The idea behind a "Capability Distribution" is really a response to this situation.
Rather than focusing on low level libraries exclusively, Spectrum
will try to assemble an ecosystem of software that is is more readily consumable
near the top of the software development food chain.  It will strive to provide readily
consumable high level end user capabilities in addition to high quality library implementations.
 
In this fashion end users focus on consuming the capability as opposed to constantly maintaining it
and most important the scope of the user community is extended beyond the traditional development
community to include actual end users.  


##Problem:

Rarely is a non-trivial computing problem solved with
a single library.  More often then not development teams are required to use 
several libraries, and as such go through the effort of integrating the functionality 
while ensuring consistency, transparency and accuracy of the results. 

The challenge with this approach is one of incomplete coverage and diversity
among teams.  It would be unlikely for multiple teams acting independently to 
integrate any arbitrary set of libraries in the same fashion.  Even the same 
team of people over long periods of time are likely to adopt different styles
and approaches.  Finally, most teams need to deal with tactical priorities
and as such integrate only what they need at a given point in time, further
impacting consistency and transparency.

##Solution:

One option would be to write a monolithic library with staggering breadth of scope
but doing so would clearly be a daunting task.  Spectrum takes a decidedly
more pragmatic approach.  Spectrum consists of a series of libraries written in Scala
which implement the functionality available in popular open source functional area
libraries.  They are not exact ports nor are they forks of the libraries from
which they were inspired.  They do however faithfully implement the algorithms and 
are similar enough to track and subsequently incorporate functionality that 
is added to the originating libraries as they themselves evolve.

An exact port wouldn't achieve the desired goal of providing consistent, transparent,
accurate and seamless integration between the universe of libraries that are included.

A fork of the existing code bases would result in significant design and standards 
variation making the resulting code base challenging to support and maintain.

That said, the goal is for Spectrum to be familiar to those who have used any of
the libraries from which Spectrum is inspired while at the same tim producing a distribution
that is easily maintained, consistent, transparent and accurate.

##Usability and integration:

Producing a library that is usable in all the contexts one might wish
to use it is always a trade off.  Despite best efforts of engineers over
the decades there is no single universal platform.  As such some platforms
are integrated more directly then others.  

Spectrum provides programmatic APIs for both Scala and Java.  

Spectrum provides  web service wrappers making it callable from any 
platform that supports that integration mechanism.  The web services may be deployed 
locally or remotely depending upon performance and scalability requirements.  

Additionally Spectrum will provide a plugin which allows it to integrate with Excel and 
Open Office.  The plugin will be written in C# thus leveraging the strength o the .NET platform
providing a thoroughly modern software stack.

##Design choices:

The obvious design omissions are an integration with native code (c/c++) and a more general
integration with .NET outside the Excel Add-in that has been mentioned.  At this
point the thought is that offering a RESTful interface, is sufficient to support 
a broad array of up stream options that easily integrate with Spectrum.  Like most
things we will re-evaluate our assumptions regularly.







