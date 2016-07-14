#Lucbir
		
本项目为模仿 Lire 框架实现的，同样是基于 Lucene 实现的，但由于 Lire 框架不容易进行扩展，
所以有了这个项目，旨在实现一个扩展性高的图像检索框架。

使用该项目只需要了解如何使用 LucbirIndexer 以及 LucbirSearcher。
在创建 LucbirIndexer 以及 LucbirSearcher 时，需要传入一个 Feature 参数。
Feature 是一个图像特征提取算法的接口，该接口给出来该项目中图像特征提取算法的规范，
但这个规范比较宽松，这使得该项目更容易扩展。

若使用该项目时，需要另外实现其他的图像特征提取算法，只需要实现 Feature 接口，
就能够利用 LucbirIndexer 和 LucbirSearcher 两个核心类，很轻易地完成索引和搜索的工作。