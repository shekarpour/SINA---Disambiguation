# SINA---Disambiguation


This package provides a disambiguation module which is an essential part in question answering systems,
This model is based on hidden markov model, as it gets a textual query and then provides a list of most likely resources from DBpedia knowledge graph.
To utilize this module you have to make a call as follows:



String inputQuery = "barak obama spouse";

HiddenMarkovModel HMM = new HiddenMarkovModel();
HMM.runModel(inputQuery);


To have more insight about the theoritical background, you can study the following papers:

1. Sina: Semantic interpretation of user queries for question answering on interlinked data. S Shekarpour, E Marx, ACN Ngomo, S Auer, Web Semantics: Science, Services and Agents on the World Wide Web 30, 39-51


2. Question answering on interlinked data. S Shekarpour, AC Ngonga Ngomo, S Auer Proceedings of the 22nd international conference on World Wide Web, 1145-1156

3. Query segmentation and resource disambiguation leveraging background knowledge
S Shekarpour, ACN Ngomo, S Auer, Proceedings of WoLE Workshop
