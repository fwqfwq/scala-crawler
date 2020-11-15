# Web-Crawler Improvement

To improve the program, concurrency would help.   

This program has the monad operations for web crawler. If the depth needed is above 1, cocurrency would help imporove the efficiency. 
In this stage, multithreading application, like Akka, could be useful in design and development.   

Actor System could be designed including: 
1. *Client Actor* to send the crawl requests  
2. *LinkChecker Actor* to check the validation of links  
3. *Crawler Actor* to collect the anchor tags and send back the message
 