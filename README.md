In this assignment, we are revisiting the **Genetic Algorithm** to solve the **Knapsack problem**. But instead of focusing on data parallelism (and performance), we are not looking to explore task parallelism, which can eventualy lead to scaling across different machines. 

The goal of assignment #3 is to reimplement the Genetic Algorithm using the **Actor Model** or Co-routines and Channels. You can use Java, Scala, Erlang, Go, Rust or other language in which one of these two models are supported.

You should design your own architecture, but as a guideline, each actor/go-routine should do only one thing. Communication happens in channels or messages, and no shared memory is allowed (otherwise, you cannot scale it across machines). But your implementation does not need to run in multiple machines. One process is fine, as long as you adhere to the programming model.
