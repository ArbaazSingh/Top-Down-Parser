# Top-Down-Parser
An object-oriented top-down parser in Java that translates every TinyPL program into an equivalent sequence of byte-codes for a Java Virtual Machine.

For each test case, we show the byte code generated, as well as the object diagram produced by JIVE at the end of execution.

#Sample Input

0: iload_1 <br />
1: iload_2 <br />
2: if_icmple 9 <br />
5: iload_1 <br />
6: iload_2 <br />
7: isub <br />
8: istore_1 <br />
9: return <br />


#Output Object Diagram
![test4](https://cloud.githubusercontent.com/assets/25837151/23141781/77ab5f96-f7de-11e6-9ad5-1ea89d4ae3ba.png)


Developed By
------------
* Arbaaz Singh Sidhu - <arbaazsi@buffalo.edu>

License
-------

    Copyright 2016 Arbaaz Singh Sidhu

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

