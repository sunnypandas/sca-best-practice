/*
 * Copyright (C) 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License", method = RequestMethod.POST);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.acgnfuns.controller;

import com.acgnfuns.service.HanlpService;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.document.sentence.Sentence;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import com.hankcs.hanlp.mining.word.WordInfo;
import com.hankcs.hanlp.seg.common.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author xiaolongzuo
 */
@RestController
@RequestMapping("/v1/hanlp")
public class HanlpController {

    @Autowired
    private HanlpService hanlpService;

    @RequestMapping(value = "/basicTokenizer", method = RequestMethod.POST)
    public List<Term> basicTokenizer(@RequestParam String text) {
        return hanlpService.basicTokenizer(text);
    }

    @RequestMapping(value = "/crfLexicalAnalyzer", method = RequestMethod.POST)
    public Sentence crfLexicalAnalyzer(@RequestParam String sentence) {
        return hanlpService.crfLexicalAnalyzer(sentence);
    }

    @RequestMapping(value = "/chineseNameRecognition", method = RequestMethod.POST)
    public List<Term> chineseNameRecognition(@RequestParam String sentence) {
        return hanlpService.chineseNameRecognition(sentence);
    }

    @RequestMapping(value = "/dependencyParser", method = RequestMethod.POST)
    public CoNLLSentence dependencyParser(@RequestParam String sentence) {
        return hanlpService.dependencyParser(sentence);
    }

    @RequestMapping(value = "/highSpeedSegment", method = RequestMethod.POST)
    public List<Term> highSpeedSegment(@RequestParam String text) {
        return hanlpService.highSpeedSegment(text);
    }

    @RequestMapping(value = "/indexSegment", method = RequestMethod.POST)
    public List<Term> indexSegment(@RequestParam String text) {
        return hanlpService.indexSegment(text);
    }

    @RequestMapping(value = "/japaneseNameRecognition", method = RequestMethod.POST)
    public List<Term> japaneseNameRecognition(@RequestParam String sentence) {
        return hanlpService.japaneseNameRecognition(sentence);
    }

    @RequestMapping(value = "/keyword", method = RequestMethod.POST)
    public List<String> keyword(@RequestParam String content, @RequestParam Integer size) {
        return hanlpService.keyword(content, size);
    }

    @RequestMapping(value = "/multithreadingSegment", method = RequestMethod.POST)
    public List<Term> multithreadingSegment(@RequestParam String text) {
        return hanlpService.multithreadingSegment(text);
    }

    @RequestMapping(value = "/nlpSegment", method = RequestMethod.POST)
    public List<Term> nlpSegment(@RequestParam String text) {
        return hanlpService.nlpSegment(text);
    }

    @RequestMapping(value = "/nShortSegment", method = RequestMethod.POST)
    public List<Term> nShortSegment(@RequestParam String sentence) {
        return hanlpService.nShortSegment(sentence);
    }

    @RequestMapping(value = "/shortestSegment", method = RequestMethod.POST)
    public List<Term> shortestSegment(@RequestParam String sentence) {
        return hanlpService.shortestSegment(sentence);
    }

    @RequestMapping(value = "/newWordDiscover", method = RequestMethod.POST)
    public List<WordInfo> newWordDiscover(@RequestParam String content, @RequestParam Integer size) {
        return hanlpService.newWordDiscover(content, size);
    }

    @RequestMapping(value = "/normalization", method = RequestMethod.POST)
    public List<List<Term>> normalization(@RequestParam String text) {
        return hanlpService.normalization(text);
    }

    @RequestMapping(value = "/numberAndQuantifierRecognition", method = RequestMethod.POST)
    public List<Term> numberAndQuantifierRecognition(@RequestParam String sentence) {
        return hanlpService.numberAndQuantifierRecognition(sentence);
    }

    @RequestMapping(value = "/occurrence", method = RequestMethod.POST)
    public Map<String, Object> occurrence(@RequestParam String text) {
        return hanlpService.occurrence(text);
    }

    @RequestMapping(value = "/organizationRecognition", method = RequestMethod.POST)
    public List<Term> organizationRecognition(@RequestParam String sentence) {
        return hanlpService.organizationRecognition(sentence);
    }

    @RequestMapping(value = "/phraseExtractor", method = RequestMethod.POST)
    public List<String> phraseExtractor(@RequestParam String text, @RequestParam Integer size) {
        return hanlpService.phraseExtractor(text, size);
    }
    @RequestMapping(value = "/pinyin", method = RequestMethod.POST)
    public List<Pinyin> pinyin(@RequestParam String text) {
        return hanlpService.pinyin(text);
    }

    @RequestMapping(value = "/placeRecognition", method = RequestMethod.POST)
    public List<Term> placeRecognition(@RequestParam String sentence) {
        return hanlpService.placeRecognition(sentence);
    }

    @RequestMapping(value = "/posTagging", method = RequestMethod.POST)
    public List<Term> posTagging(@RequestParam String text) {
        return hanlpService.posTagging(text);
    }

    @RequestMapping(value = "/rewriteText", method = RequestMethod.POST)
    public String rewriteText(@RequestParam String text) {
        return hanlpService.rewriteText(text);
    }

    @RequestMapping(value = "/segment", method = RequestMethod.POST)
    public List<Term> segment(@RequestParam String sentence) {
        return hanlpService.segment(sentence);
    }

    @RequestMapping(value = "/stopWord", method = RequestMethod.POST)
    public List<Term> stopWord(@RequestParam String text, @RequestParam List<String> stopWords) {
        return hanlpService.stopWord(text, stopWords);
    }

    @RequestMapping(value = "/suggester", method = RequestMethod.POST)
    public List<String> suggester(@RequestParam List<String> titles, @RequestParam String key, @RequestParam Integer size) {
        return hanlpService.suggester(titles, key, size);
    }

    @RequestMapping(value = "/summary", method = RequestMethod.POST)
    public List<String> summary(@RequestParam String document, @RequestParam Integer size) {
        return hanlpService.summary(document, size);
    }

    @RequestMapping(value = "/tokenizerConfig", method = RequestMethod.POST)
    public List<Term> tokenizerConfig(@RequestParam String text) {
        return hanlpService.tokenizerConfig(text);
    }

    @RequestMapping(value = "/convertToTraditionalChinese", method = RequestMethod.POST)
    public String convertToTraditionalChinese(@RequestParam String simplifiedChineseString) {
        return hanlpService.convertToTraditionalChinese(simplifiedChineseString);
    }

    @RequestMapping(value = "/convertToSimplifiedChinese", method = RequestMethod.POST)
    public String convertToSimplifiedChinese(@RequestParam String traditionalChineseString) {
        return hanlpService.convertToSimplifiedChinese(traditionalChineseString);
    }

    @RequestMapping(value = "/traditionalChineseSegment", method = RequestMethod.POST)
    public List<Term> traditionalChineseSegment(@RequestParam String text) {
        return hanlpService.traditionalChineseSegment(text);
    }

    @RequestMapping(value = "/translatedNameRecognition", method = RequestMethod.POST)
    public List<Term> translatedNameRecognition(@RequestParam String sentence) {
        return hanlpService.translatedNameRecognition(sentence);
    }

    @RequestMapping(value = "/urlRecognition", method = RequestMethod.POST)
    public List<Term> urlRecognition(@RequestParam String text) {
        return hanlpService.urlRecognition(text);
    }

    @RequestMapping(value = "/wordDistance", method = RequestMethod.POST)
    public Long wordDistance(@RequestParam String wordA, @RequestParam String wordB) {
        return hanlpService.wordDistance(wordA, wordB);
    }

    @RequestMapping(value = "/wordSimilarity", method = RequestMethod.POST)
    public Double wordSimilarity(@RequestParam String wordA, @RequestParam String wordB) {
        return hanlpService.wordSimilarity(wordA, wordB);
    }
}
