package com.acgnfuns.service;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.document.sentence.Sentence;
import com.hankcs.hanlp.corpus.occurrence.Occurrence;
import com.hankcs.hanlp.corpus.occurrence.PairFrequency;
import com.hankcs.hanlp.corpus.occurrence.TermFrequency;
import com.hankcs.hanlp.corpus.occurrence.TriaFrequency;
import com.hankcs.hanlp.dictionary.CoreSynonymDictionary;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.mining.word.WordInfo;
import com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.Viterbi.ViterbiSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.suggest.Suggester;
import com.hankcs.hanlp.tokenizer.*;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class HanlpService {

    /**
     * 演示基础分词，基础分词只进行基本NGram分词，不识别命名实体，不使用用户词典
     *
     * @author sunpanpan
     */
    public List<Term> basicTokenizer(String text) {
        return BasicTokenizer.segment(text);
    }

    /**
     * CRF词法分析器
     * 自1.6.6版起模型格式不兼容旧版：CRF模型为对数线性模型{@link com.hankcs.hanlp.model.crf.LogLinearModel}，
     * 通过复用结构化感知机的维特比解码算法，效率提高10倍。
     *
     * @author sunpanpan
     */
    public Sentence crfLexicalAnalyzer(String sentence) {
        CRFLexicalAnalyzer analyzer = null;
        try {
            analyzer = new CRFLexicalAnalyzer();
            return analyzer.analyze(sentence);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 中国人名识别
     * @author sunpanpan
     */
    public List<Term> chineseNameRecognition(String sentence) {
        Segment segment = HanLP.newSegment().enableNameRecognize(true);
        return segment.seg(sentence);
    }

    /**
     * 依存句法分析（神经网络句法模型需要-Xms1g -Xmx1g -Xmn512m）
     *
     * @author sunpanpan
     */
    public CoNLLSentence dependencyParser(String sentence) {
        return HanLP.parseDependency(sentence);
    }

    /**
     * 演示极速分词，基于DoubleArrayTrie实现的词典正向最长分词，适用于“高吞吐量”“精度一般”的场合
     * @author sunpanpan
     */
    public List<Term> highSpeedSegment(String text) {
        HanLP.Config.ShowTermNature = false;
        return SpeedTokenizer.segment(text);
    }

    /**
     * 索引分词
     * @author sunpanpan
     */
    public List<Term> indexSegment(String text) {
        return IndexTokenizer.segment(text);
    }

    /**
     * 日本人名识别
     * @author sunpanpan
     */
    public List<Term> japaneseNameRecognition(String sentence) {
        Segment segment = HanLP.newSegment().enableJapaneseNameRecognize(true);
        return segment.seg(sentence);
    }

    /**
     * 关键词提取
     * @author sunpanpan
     */
    public List<String> keyword(String content, Integer size) {
        return HanLP.extractKeyword(content, size);
    }

    /**
     * 演示多线程并行分词
     * 由于HanLP的任何分词器都是线程安全的，所以用户只需调用一个配置接口就可以启用任何分词器的并行化
     * @author sunpanpan
     */
    public List<Term> multithreadingSegment(String text) {
        Segment segment = null;
        try {
            segment = new CRFLexicalAnalyzer(HanLP.Config.CRFCWSModelPath).enableCustomDictionary(false);
            HanLP.Config.ShowTermNature = false;
            segment.enableMultithreading(true);
            return segment.seg(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * NLP分词，更精准的中文分词、词性标注与命名实体识别。
     * 语料库规模决定实际效果，面向生产环境的语料库应当在千万字量级。欢迎用户在自己的语料上训练新模型以适应新领域、识别新的命名实体。
     * 标注集请查阅 https://github.com/hankcs/HanLP/blob/master/data/dictionary/other/TagPKU98.csv
     * 或者干脆调用 Sentence#translateLabels() 转为中文
     * @author sunpanpan
     */
    public List<Term> nlpSegment(String text) {
        NLPTokenizer.ANALYZER.enableCustomDictionary(false);
        return NLPTokenizer.segment(text);
    }

    /**
     * N最短路径分词，该分词器比最短路分词器慢，但是效果稍微好一些，对命名实体识别能力更强
     * @author sunpanpan
     */
    public List<Term> nShortSegment(String sentence) {
        Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        return nShortSegment.seg(sentence);
    }

    /**
     * 最短路径分词
     * @author sunpanpan
     */
    public List<Term> shortestSegment(String sentence) {
        Segment shortestSegment = new ViterbiSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        return shortestSegment.seg(sentence);
    }

    /**
     * 词语提取、新词发现
     * @author sunpanpan
     */
    public List<WordInfo> newWordDiscover(String content, Integer size) {
        try {
            return HanLP.extractWords(new BufferedReader(new StringReader(content)), size);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 演示自动去除停用词、自动断句的分词器
     * @author sunpanpan
     */
    public List<List<Term>> normalization(String text) {
        return NotionalTokenizer.seg2sentence(text);
    }

    /**
     * 演示数词和数量词识别
     * @author sunpanpan
     */
    public List<Term> numberAndQuantifierRecognition(String sentence) {
        StandardTokenizer.SEGMENT.enableNumberQuantifierRecognize(true);
        return StandardTokenizer.segment(sentence);
    }

    /**
     * 演示数词和数量词识别
     * @author sunpanpan
     */
    public Map<String, Object> occurrence(String text) {
        Occurrence occurrence = new Occurrence();
        occurrence.addAll(text);
        occurrence.compute();
        Set<Map.Entry<String, TermFrequency>> uniGram = occurrence.getUniGram();
        Set<Map.Entry<String, PairFrequency>> biGram = occurrence.getBiGram();
        Set<Map.Entry<String, TriaFrequency>> triGram = occurrence.getTriGram();
        Map<String, Object> map = new HashMap<>();
        map.put("UniGram", uniGram);
        map.put("BiGram", biGram);
        map.put("TriGram", triGram);
        return map;
    }

    /**
     * 机构名识别
     * @author sunpanpan
     */
    public List<Term> organizationRecognition(String sentence) {
        Segment segment = HanLP.newSegment().enableCustomDictionary(false).enableOrganizationRecognize(true);
        return segment.seg(sentence);
    }

    /**
     * 汉字转拼音
     * @author sunpanpan
     */
    public List<String> phraseExtractor(String text, Integer size) {
        return HanLP.extractPhrase(text, size);
    }

    /**
     * 短语提取
     * @author sunpanpan
     */
    public List<Pinyin> pinyin(String text) {
        return HanLP.convertToPinyinList(text);
    }

    /**
     * 地名识别
     * @author sunpanpan
     */
    public List<Term> placeRecognition(String sentence) {
        Segment segment = HanLP.newSegment().enablePlaceRecognize(true);
        return segment.seg(sentence);
    }

    /**
     * 词性标注
     * @author sunpanpan
     */
    public List<Term> posTagging(String text) {
        Segment segment = HanLP.newSegment();
        segment.enablePartOfSpeechTagging(true);
        return segment.seg(text);
    }

    /**
     * 文本重写
     * @author sunpanpan
     */
    public String rewriteText(String text) {
        return CoreSynonymDictionary.rewrite(text);
    }

    /**
     * 标准分词
     * @author sunpanpan
     */
    public List<Term> segment(String sentence) {
        return HanLP.segment(sentence);
    }

    /**
     * 演示如何去除停用词
     * @author sunpanpan
     */
    public List<Term> stopWord(String text, List<String> stopWords) {
        for (String word: stopWords)
            CoreStopWordDictionary.add(word);
        return NotionalTokenizer.segment(text);
    }

    /**
     * 文本推荐(句子级别，从一系列句子中挑出与输入句子最相似的那一个)
     * @author sunpanpan
     */
    public List<String> suggester(List<String> titles, String key, Integer size) {
        Suggester suggester = new Suggester();
        for (String title: titles)
        {
            suggester.addSentence(title);
        }
        return suggester.suggest(key, size);
    }

    /**
     * 自动摘要
     * @author sunpanpan
     */
    public List<String> summary(String document, Integer size) {
        return HanLP.extractSummary(document, size);
    }

    /**
     * 演示动态设置预置分词器，这里的设置是全局的
     * @author sunpanpan
     */
    public List<Term> tokenizerConfig(String text) {
        StandardTokenizer.SEGMENT.enableAllNamedEntityRecognize(true);
        return StandardTokenizer.segment(text);
    }

    /**
     * 将简繁转换做到极致
     * @author sunpanpan
     */
    public String convertToTraditionalChinese(String simplifiedChineseString) {
        return HanLP.convertToTraditionalChinese(simplifiedChineseString);
    }

    /**
     * 将简繁转换做到极致
     * @author sunpanpan
     */
    public String convertToSimplifiedChinese(String traditionalChineseString) {
        return HanLP.convertToSimplifiedChinese(traditionalChineseString);
    }

    /**
     * 繁体中文分词
     * @author sunpanpan
     */
    public List<Term> traditionalChineseSegment(String text) {
        return TraditionalChineseTokenizer.segment(text);
    }

    /**
     * 音译人名识别
     * @author sunpanpan
     */
    public List<Term> translatedNameRecognition(String sentence) {
        Segment segment = HanLP.newSegment().enableTranslatedNameRecognize(true);
        return segment.seg(sentence);
    }

    /**
     * 音译人名识别
     * @author sunpanpan
     */
    public List<Term> urlRecognition(String text) {
        return URLTokenizer.segment(text);
    }

    /**
     * 语义距离
     * @author sunpanpan
     */
    public Long wordDistance(String wordA, String wordB) {
        return CoreSynonymDictionary.distance(wordA, wordB);
    }

    /**
     * 语义相似度
     * @author sunpanpan
     */
    public Double wordSimilarity(String wordA, String wordB) {
        return CoreSynonymDictionary.similarity(wordA, wordB);
    }
}
