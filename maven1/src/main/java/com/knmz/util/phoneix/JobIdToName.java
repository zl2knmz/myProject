package com.knmz.util.phoneix;

import com.alibaba.fastjson.JSONObject;


/**
 * @Author: zl
 * @Date: 2019/6/24 11:44
 * 用户宽表中，职位id -> 职位名
 */
public class JobIdToName {
    private final String json = "{\"10\":\"技术\",\"11\":\"产品\",\"12\":\"设计\",\"13\":\"运营\",\"14\":\"市场\",\"15\":\"人事\",\"16\":\"高级管理\",\"17\":\"销售\",\"18\":\"媒体\",\"19\":\"金融\",\"20\":\"汽车\",\"21\":\"教育培训\",\"22\":\"医疗健康\",\"23\":\"采购贸易\",\"24\":\"供应链/物流\",\"25\":\"房地产/建筑\",\"26\":\"咨询/翻译/法律\",\"27\":\"实习生/管培生\",\"28\":\"旅游\",\"29\":\"服务业\",\"30\":\"生产制造\",\"1001\":\"后端开发\",\"1002\":\"移动开发\",\"1003\":\"测试\",\"1004\":\"运维/技术支持\",\"1005\":\"数据\",\"1006\":\"项目管理\",\"1007\":\"硬件开发\",\"1008\":\"前端开发\",\"1009\":\"通信\",\"1010\":\"电子/半导体\",\"1011\":\"高端技术职位\",\"1012\":\"人工智能\",\"1013\":\"软件销售支持\",\"1014\":\"其他技术职位\",\"1101\":\"产品经理\",\"1102\":\"高端产品职位\",\"1103\":\"其他产品职位\",\"1201\":\"视觉设计\",\"1202\":\"交互设计\",\"1203\":\"用户研究\",\"1204\":\"高端设计职位\",\"1205\":\"非视觉设计\",\"1206\":\"其他设计职位\",\"1301\":\"运营\",\"1302\":\"编辑\",\"1303\":\"客服\",\"1304\":\"高端运营职位\",\"1305\":\"其他运营职位\",\"1401\":\"市场/营销\",\"1402\":\"公关媒介\",\"1403\":\"会务会展\",\"1404\":\"广告\",\"1405\":\"高端市场职位\",\"1406\":\"其他市场职位\",\"1501\":\"人力资源\",\"1502\":\"行政\",\"1503\":\"财务\",\"1504\":\"法务\",\"1505\":\"其他职能职位\",\"1601\":\"高级管理职位\",\"1701\":\"销售\",\"1702\":\"销售管理\",\"1703\":\"其他销售职位\",\"1801\":\"采编/写作/出版\",\"1802\":\"公关媒介\",\"1803\":\"会务会展\",\"1804\":\"广告\",\"1805\":\"影视媒体\",\"1806\":\"其他传媒职位\",\"1901\":\"投融资\",\"1902\":\"风控\",\"1903\":\"税务审计\",\"1904\":\"银行\",\"1905\":\"互联网金融\",\"1906\":\"保险\",\"1907\":\"证券\",\"1908\":\"其他金融职位\",\"2001\":\"汽车设计与研发\",\"2002\":\"汽车生产与制造\",\"2003\":\"汽车销售与服务\",\"2004\":\"其他汽车职位\",\"2101\":\"教育产品研发\",\"2102\":\"教育行政\",\"2103\":\"教师\",\"2104\":\"IT培训\",\"2105\":\"职业培训\",\"2106\":\"招生\",\"2107\":\"教练\",\"2108\":\"其他教育培训职位\",\"2201\":\"医生/医技\",\"2202\":\"护士/护理\",\"2203\":\"健康整形\",\"2204\":\"生物制药\",\"2205\":\"医疗器械\",\"2206\":\"药店\",\"2207\":\"市场营销/媒体\",\"2208\":\"其他医疗健康类职位\",\"2301\":\"采购\",\"2302\":\"进出口贸易\",\"2303\":\"其他采购/贸易职位\",\"2401\":\"物流\",\"2402\":\"仓储\",\"2403\":\"运输\",\"2404\":\"高端供应链职位\",\"2405\":\"其他供应链职位\",\"2501\":\"房地产规划开发\",\"2502\":\"设计装修与市政建设\",\"2503\":\"房地产经纪\",\"2504\":\"物业管理\",\"2505\":\"高端房地产职位\",\"2506\":\"其他房地产职位\",\"2601\":\"咨询/调研\",\"2602\":\"律师\",\"2603\":\"翻译\",\"2604\":\"高端咨询类职位\",\"2605\":\"其他咨询类职位\",\"2701\":\"实习生/培训生/储备干部\",\"2702\":\"其他实习/培训/储备职位\",\"2801\":\"旅游服务\",\"2802\":\"旅游产品开发/策划\",\"2803\":\"其他旅游职位\",\"2901\":\"安保/家政\",\"2902\":\"酒店\",\"2903\":\"餐饮\",\"2904\":\"零售\",\"2905\":\"美容/健身\",\"2906\":\"其他服务业职位\",\"3001\":\"生产营运\",\"3002\":\"质量安全\",\"3003\":\"机械设计/制造\",\"3004\":\"化工\",\"3005\":\"服装/纺织/皮革\",\"3006\":\"技工/普工\",\"3007\":\"其他生产制造职位\",\"100101\":\"后端开发\",\"100102\":\"Java\",\"100103\":\"C++\",\"100104\":\"PHP\",\"100105\":\"数据挖掘\",\"100106\":\"C\",\"100107\":\"C\",\"100108\":\".NET\",\"100109\":\"Hadoop\",\"100110\":\"Python\",\"100111\":\"Delphi\",\"100112\":\"VB\",\"100113\":\"Perl\",\"100114\":\"Ruby\",\"100115\":\"Node.js\",\"100116\":\"搜索算法\",\"100117\":\"Golang\",\"100118\":\"自然语言处理\",\"100119\":\"推荐算法\",\"100120\":\"Erlang\",\"100121\":\"算法工程师\",\"100122\":\"语音/视频/图形开发\",\"100123\":\"数据采集\",\"100201\":\"移动开发\",\"100202\":\"HTML5\",\"100203\":\"Android\",\"100204\":\"iOS\",\"100205\":\"WP\",\"100206\":\"移动web前端\",\"100207\":\"Flash\",\"100208\":\"JavaScript\",\"100209\":\"U3D\",\"100210\":\"COCOS2DX\",\"100301\":\"测试工程师\",\"100302\":\"自动化测试\",\"100303\":\"功能测试\",\"100304\":\"性能测试\",\"100305\":\"测试开发\",\"100306\":\"移动端测试\",\"100307\":\"游戏测试\",\"100308\":\"硬件测试\",\"100309\":\"软件测试\",\"100401\":\"运维工程师\",\"100402\":\"运维开发工程师\",\"100403\":\"网络工程师\",\"100404\":\"系统工程师\",\"100405\":\"IT技术支持\",\"100406\":\"系统管理员\",\"100407\":\"网络安全\",\"100408\":\"系统安全\",\"100409\":\"DBA\",\"100501\":\"数据\",\"100502\":\"ETL工程师\",\"100503\":\"数据仓库\",\"100504\":\"数据开发\",\"100505\":\"数据挖掘\",\"100506\":\"数据分析师\",\"100507\":\"数据架构师\",\"100508\":\"算法研究员\",\"100601\":\"项目经理\",\"100602\":\"项目主管\",\"100603\":\"项目助理\",\"100604\":\"项目专员\",\"100605\":\"实施顾问\",\"100606\":\"实施工程师\",\"100607\":\"需求分析工程师\",\"100701\":\"硬件\",\"100702\":\"嵌入式\",\"100703\":\"自动化\",\"100704\":\"单片机\",\"100705\":\"电路设计\",\"100706\":\"驱动开发\",\"100707\":\"系统集成\",\"100708\":\"FPGA开发\",\"100709\":\"DSP开发\",\"100710\":\"ARM开发\",\"100711\":\"PCB工艺\",\"100712\":\"模具设计\",\"100713\":\"热传导\",\"100714\":\"材料工程师\",\"100715\":\"精益工程师\",\"100716\":\"射频工程师\",\"100801\":\"前端开发\",\"100802\":\"web前端\",\"100803\":\"Javascript\",\"100804\":\"Flash\",\"100805\":\"HTML5\",\"100901\":\"通信技术工程师\",\"100902\":\"通信研发工程师\",\"100903\":\"数据通信工程师\",\"100904\":\"移动通信工程师\",\"100905\":\"电信网络工程师\",\"100906\":\"电信交换工程师\",\"100907\":\"有线传输工程师\",\"100908\":\"无线射频工程师\",\"100909\":\"通信电源工程师\",\"100910\":\"通信标准化工程师\",\"100911\":\"通信项目专员\",\"100912\":\"通信项目经理\",\"100913\":\"核心网工程师\",\"100914\":\"通信测试工程师\",\"100915\":\"通信设备工程师\",\"100916\":\"光通信工程师\",\"100917\":\"光传输工程师\",\"100918\":\"光网络工程师\",\"101001\":\"电子工程师\",\"101002\":\"电气工程师\",\"101003\":\"FAE\",\"101004\":\"电气设计工程师\",\"101101\":\"高端技术职位\",\"101102\":\"技术经理\",\"101103\":\"技术总监\",\"101104\":\"测试经理\",\"101105\":\"架构师\",\"101106\":\"CTO\",\"101107\":\"运维总监\",\"101108\":\"技术合伙人\",\"101201\":\"人工智能\",\"101202\":\"机器学习\",\"101203\":\"深度学习\",\"101204\":\"图像算法\",\"101205\":\"图像处理\",\"101206\":\"语音识别\",\"101207\":\"图像识别\",\"101208\":\"算法研究员\",\"101301\":\"软件销售支持\",\"101302\":\"售前工程师\",\"101303\":\"售后工程师\",\"101401\":\"其他技术职位\",\"110101\":\"产品经理\",\"110102\":\"网页产品经理\",\"110103\":\"移动产品经理\",\"110104\":\"产品助理\",\"110105\":\"数据产品经理\",\"110106\":\"电商产品经理\",\"110107\":\"游戏策划\",\"110108\":\"产品专员\",\"110201\":\"高端产品职位\",\"110202\":\"产品总监\",\"110203\":\"游戏制作人\",\"110204\":\"产品VP\",\"110301\":\"其他产品职位\",\"120101\":\"视觉设计\",\"120102\":\"视觉设计师\",\"120103\":\"网页设计师\",\"120104\":\"Flash设计师\",\"120105\":\"APP设计师\",\"120106\":\"UI设计师\",\"120107\":\"平面设计师\",\"120108\":\"美术设计师（2D/3D）\",\"120109\":\"广告设计师\",\"120110\":\"多媒体设计师\",\"120111\":\"原画师\",\"120112\":\"游戏特效\",\"120113\":\"游戏界面设计师\",\"120114\":\"游戏场景\",\"120115\":\"游戏角色\",\"120116\":\"游戏动作\",\"120117\":\"三维/CAD/制图\",\"120118\":\"美工\",\"120119\":\"包装设计\",\"120120\":\"设计师助理\",\"120121\":\"动画设计师\",\"120122\":\"插画师\",\"120201\":\"交互设计师\",\"120202\":\"无线交互设计师\",\"120203\":\"网页交互设计师\",\"120204\":\"硬件交互设计师\",\"120301\":\"数据分析师\",\"120302\":\"用户研究员\",\"120303\":\"游戏数值策划\",\"120304\":\"UX设计师\",\"120305\":\"用户研究经理\",\"120306\":\"用户研究总监\",\"120401\":\"高端设计职位\",\"120402\":\"设计经理/主管\",\"120403\":\"设计总监\",\"120404\":\"视觉设计经理\",\"120405\":\"视觉设计总监\",\"120406\":\"交互设计经理/主管\",\"120407\":\"交互设计总监\",\"120501\":\"非视觉设计\",\"120502\":\"服装设计\",\"120503\":\"工业设计\",\"120504\":\"橱柜设计\",\"120505\":\"家具设计\",\"120506\":\"家居设计\",\"120507\":\"珠宝设计\",\"120508\":\"室内设计\",\"120509\":\"陈列设计\",\"120510\":\"景观设计\",\"120601\":\"其他设计职位\",\"130101\":\"运营\",\"130102\":\"用户运营\",\"130103\":\"产品运营\",\"130104\":\"数据运营\",\"130105\":\"内容运营\",\"130106\":\"活动运营\",\"130107\":\"商家运营\",\"130108\":\"品类运营\",\"130109\":\"游戏运营\",\"130110\":\"网络推广\",\"130111\":\"网站运营\",\"130112\":\"新媒体运营\",\"130113\":\"社区运营\",\"130114\":\"微信运营\",\"130115\":\"微博运营\",\"130116\":\"策略运营\",\"130117\":\"线下拓展运营\",\"130118\":\"电商运营\",\"130119\":\"运营助理/专员\",\"130120\":\"内容审核\",\"130121\":\"销售运营\",\"130201\":\"编辑\",\"130202\":\"副主编\",\"130203\":\"内容编辑\",\"130204\":\"文案策划\",\"130205\":\"网站编辑\",\"130206\":\"记者\",\"130207\":\"采编\",\"130301\":\"售前咨询\",\"130302\":\"售后咨询\",\"130303\":\"网络客服\",\"130304\":\"客服经理\",\"130305\":\"客服专员/助理\",\"130306\":\"客服主管\",\"130307\":\"客服总监\",\"130308\":\"电话客服\",\"130309\":\"咨询热线/呼叫中心客服\",\"130401\":\"高端运营职位\",\"130402\":\"主编\",\"130403\":\"运营总监\",\"130404\":\"COO\",\"130405\":\"客服总监\",\"130406\":\"运营经理/主管\",\"130501\":\"其他运营职位\",\"140101\":\"选址开发\",\"140102\":\"市场营销\",\"140103\":\"市场策划\",\"140104\":\"市场顾问\",\"140105\":\"市场推广\",\"140106\":\"SEO\",\"140107\":\"SEM\",\"140108\":\"商务渠道\",\"140109\":\"商业数据分析\",\"140110\":\"活动策划\",\"140111\":\"网络营销\",\"140112\":\"海外市场\",\"140113\":\"政府关系\",\"140114\":\"APP推广\",\"140201\":\"公关媒介\",\"140202\":\"媒介经理\",\"140203\":\"广告协调\",\"140204\":\"品牌公关\",\"140205\":\"媒介专员\",\"140206\":\"活动策划执行\",\"140207\":\"媒介策划\",\"140301\":\"会务会展\",\"140302\":\"会议活动销售\",\"140303\":\"会议活动策划\",\"140304\":\"会议活动执行\",\"140305\":\"会展活动销售\",\"140306\":\"会展活动策划\",\"140307\":\"会展活动执行\",\"140401\":\"广告\",\"140402\":\"广告创意\",\"140403\":\"美术指导\",\"140404\":\"广告设计师\",\"140405\":\"策划经理\",\"140406\":\"文案\",\"140407\":\"广告制作\",\"140408\":\"媒介投放\",\"140409\":\"媒介合作\",\"140410\":\"媒介顾问\",\"140411\":\"广告审核\",\"140501\":\"高端市场职位\",\"140502\":\"市场总监\",\"140503\":\"CMO\",\"140504\":\"公关总监\",\"140505\":\"媒介总监\",\"140506\":\"创意总监\",\"140601\":\"其他市场职位\",\"150101\":\"人力资源主管\",\"150102\":\"招聘\",\"150103\":\"HRBP\",\"150104\":\"人力资源专员/助理\",\"150105\":\"培训\",\"150106\":\"薪资福利\",\"150107\":\"绩效考核\",\"150108\":\"人力资源经理\",\"150109\":\"人力资源VP/CHO\",\"150110\":\"人力资源总监\",\"150111\":\"员工关系\",\"150112\":\"组织发展\",\"150201\":\"行政专员/助理\",\"150202\":\"前台\",\"150203\":\"行政主管\",\"150204\":\"经理助理\",\"150205\":\"后勤\",\"150206\":\"商务司机\",\"150207\":\"行政经理\",\"150208\":\"行政总监\",\"150301\":\"财务\",\"150302\":\"会计\",\"150303\":\"出纳\",\"150304\":\"财务顾问\",\"150305\":\"结算\",\"150306\":\"税务\",\"150307\":\"审计\",\"150308\":\"风控\",\"150309\":\"财务经理\",\"150310\":\"CFO\",\"150311\":\"财务总监\",\"150312\":\"财务主管\",\"150401\":\"法务专员/助理\",\"150402\":\"律师\",\"150403\":\"专利\",\"150404\":\"法律顾问\",\"150405\":\"法务主管\",\"150406\":\"法务经理\",\"150407\":\"法务总监\",\"150501\":\"其他职能职位\",\"160101\":\"高级管理职位\",\"160102\":\"CEO/总裁/总经理\",\"160103\":\"副总裁/副总经理\",\"160104\":\"事业部负责人\",\"160105\":\"区域/分公司/代表处负责人\",\"160106\":\"总裁/总经理/董事长助理\",\"160107\":\"合伙人\",\"160108\":\"创始人\",\"160109\":\"董事会秘书\",\"170101\":\"销售\",\"170102\":\"销售专员\",\"170103\":\"销售经理\",\"170104\":\"客户代表\",\"170105\":\"大客户代表\",\"170106\":\"BD经理\",\"170107\":\"商务渠道\",\"170108\":\"渠道销售\",\"170109\":\"代理商销售\",\"170110\":\"销售助理\",\"170111\":\"电话销售\",\"170112\":\"销售顾问\",\"170113\":\"商品经理\",\"170114\":\"广告销售\",\"170115\":\"网络营销\",\"170116\":\"营销主管\",\"170117\":\"销售工程师\",\"170118\":\"客户经理\",\"170201\":\"销售管理\",\"170202\":\"销售总监\",\"170203\":\"商务总监\",\"170204\":\"区域总监\",\"170205\":\"城市经理\",\"170206\":\"销售VP\",\"170207\":\"团队经理\",\"170301\":\"其他销售职位\",\"180101\":\"采编/写作/出版\",\"180102\":\"记者\",\"180103\":\"编辑\",\"180104\":\"采编\",\"180105\":\"撰稿人\",\"180106\":\"出版发行\",\"180107\":\"校对录入\",\"180108\":\"总编\",\"180109\":\"自媒体\",\"180201\":\"公关媒介\",\"180202\":\"媒介经理\",\"180203\":\"媒介专员\",\"180204\":\"广告协调\",\"180205\":\"品牌公关\",\"180206\":\"活动策划执行\",\"180207\":\"媒介策划\",\"180301\":\"会务会展\",\"180302\":\"会议活动销售\",\"180303\":\"会议活动策划\",\"180304\":\"会议活动执行\",\"180305\":\"会展活动销售\",\"180306\":\"会展活动策划\",\"180307\":\"会展活动执行\",\"180401\":\"广告\",\"180402\":\"广告创意\",\"180403\":\"美术指导\",\"180404\":\"广告设计师\",\"180405\":\"策划经理\",\"180406\":\"文案\",\"180407\":\"广告制作\",\"180408\":\"媒介投放\",\"180409\":\"媒介合作\",\"180410\":\"媒介顾问\",\"180411\":\"广告审核\",\"180501\":\"影视媒体\",\"180502\":\"助理\",\"180503\":\"统筹制片人\",\"180504\":\"执行制片人\",\"180505\":\"导演/编导\",\"180506\":\"摄影/影像师\",\"180507\":\"视频编辑\",\"180508\":\"音频编辑\",\"180509\":\"经纪人\",\"180510\":\"后期制作\",\"180511\":\"影视制作\",\"180512\":\"影视发行\",\"180513\":\"影视策划\",\"180514\":\"主持人/主播/DJ\",\"180515\":\"演员/配音/模特\",\"180516\":\"化妆/造型/服装\",\"180517\":\"放映管理\",\"180518\":\"录音/音效\",\"180519\":\"制片人\",\"180520\":\"编剧\",\"180601\":\"其他传媒职位\",\"190101\":\"投融资\",\"190102\":\"投资经理\",\"190103\":\"行业研究\",\"190104\":\"资产管理\",\"190105\":\"投资总监\",\"190106\":\"投资VP\",\"190107\":\"投资合伙人\",\"190108\":\"融资\",\"190109\":\"并购\",\"190110\":\"投后管理\",\"190111\":\"投资助理\",\"190112\":\"其他投融资职位\",\"190113\":\"投资顾问\",\"190201\":\"风控\",\"190202\":\"律师\",\"190203\":\"资信评估\",\"190204\":\"合规稽查\",\"190301\":\"审计\",\"190302\":\"法务\",\"190303\":\"会计\",\"190304\":\"清算\",\"190401\":\"银行\",\"190402\":\"信用卡销售\",\"190403\":\"分析师\",\"190404\":\"柜员\",\"190405\":\"商务渠道\",\"190406\":\"大堂经理\",\"190407\":\"理财顾问\",\"190408\":\"客户经理\",\"190409\":\"信贷管理\",\"190410\":\"风控\",\"190501\":\"互联网金融\",\"190502\":\"金融产品经理\",\"190503\":\"风控\",\"190504\":\"催收员\",\"190505\":\"分析师\",\"190506\":\"投资经理\",\"190507\":\"交易员\",\"190508\":\"理财顾问\",\"190509\":\"合规稽查\",\"190510\":\"审计\",\"190511\":\"清算\",\"190601\":\"保险业务\",\"190602\":\"精算师\",\"190603\":\"保险理赔\",\"190701\":\"证券\",\"190702\":\"证券经纪人\",\"190703\":\"证券分析师\",\"190801\":\"其他金融职位\",\"200101\":\"汽车设计\",\"200102\":\"车身设计\",\"200103\":\"底盘设计\",\"200104\":\"机械设计\",\"200105\":\"动力系统设计\",\"200106\":\"电子工程设计\",\"200107\":\"零部件设计\",\"200108\":\"汽车工程项目管理\",\"200109\":\"内外饰设计工程师\",\"200201\":\"总装工程师\",\"200202\":\"焊接工程师\",\"200203\":\"冲压工程师\",\"200204\":\"质量工程师\",\"200301\":\"汽车销售与制造\",\"200302\":\"汽车销售\",\"200303\":\"汽车配件销售\",\"200304\":\"汽车售后服务\",\"200305\":\"汽车维修\",\"200306\":\"汽车美容\",\"200307\":\"汽车定损理赔\",\"200308\":\"二手车评估师\",\"200309\":\"4S店管理\",\"200310\":\"汽车改装工程师\",\"200401\":\"其他汽车职位\",\"210101\":\"教育产品研发\",\"210102\":\"课程设计\",\"210103\":\"课程编辑\",\"210104\":\"教师\",\"210105\":\"培训研究\",\"210106\":\"培训师\",\"210107\":\"培训策划\",\"210108\":\"其他教育产品研发职位\",\"210201\":\"教育行政\",\"210202\":\"校长\",\"210203\":\"教务管理\",\"210204\":\"教学管理\",\"210205\":\"班主任/辅导员\",\"210301\":\"教师\",\"210302\":\"助教\",\"210303\":\"高中教师\",\"210304\":\"初中教师\",\"210305\":\"小学教师\",\"210306\":\"幼教\",\"210307\":\"理科教师\",\"210308\":\"文科教师\",\"210309\":\"外语教师\",\"210310\":\"音乐教师\",\"210311\":\"美术教师\",\"210312\":\"体育教师\",\"210313\":\"就业老师\",\"210401\":\"IT培训\",\"210402\":\"JAVA培训讲师\",\"210403\":\"Android培训讲师\",\"210404\":\"ios培训讲师\",\"210405\":\"PHP培训讲师\",\"210406\":\".NET培训讲师\",\"210407\":\"C++培训讲师\",\"210408\":\"Unity3D培训讲师\",\"210409\":\"Web前端培训讲师\",\"210410\":\"软件测试培训讲师\",\"210411\":\"动漫培训讲师\",\"210412\":\"UI设计培训讲师\",\"210501\":\"财会培训讲师\",\"210502\":\"HR培训讲师\",\"210503\":\"培训师\",\"210504\":\"拓展培训\",\"210601\":\"课程顾问\",\"210602\":\"招生顾问\",\"210603\":\"留学顾问\",\"210701\":\"教练\",\"210702\":\"舞蹈教练\",\"210703\":\"瑜伽教练\",\"210704\":\"瘦身顾问\",\"210705\":\"游泳教练\",\"210706\":\"健身教练\",\"210707\":\"篮球/羽毛球教练\",\"210708\":\"跆拳道教练\",\"210801\":\"其他教育培训职位\",\"220101\":\"医生/医技\",\"220102\":\"医生助理\",\"220103\":\"医学影像\",\"220104\":\"B超医生\",\"220105\":\"中医\",\"220106\":\"医师\",\"220107\":\"心理医生\",\"220108\":\"药剂师\",\"220109\":\"牙科医生\",\"220110\":\"康复治疗师\",\"220111\":\"验光师\",\"220112\":\"放射科医师\",\"220113\":\"检验科医师\",\"220114\":\"医师\",\"220115\":\"其他医生职位\",\"220201\":\"护士长\",\"220202\":\"护士/护理\",\"220203\":\"导医\",\"220301\":\"健康整形\",\"220302\":\"营养师\",\"220303\":\"整形师\",\"220304\":\"理疗师\",\"220305\":\"针灸推拿\",\"220401\":\"生物制药\",\"220402\":\"药品注册\",\"220403\":\"药品生产\",\"220404\":\"临床研究\",\"220405\":\"临床协调\",\"220406\":\"临床数据分析\",\"220407\":\"医学总监\",\"220408\":\"医药研发\",\"220501\":\"医疗器械注册\",\"220502\":\"医疗器械生产/质量管理\",\"220503\":\"医疗器械研究\",\"220601\":\"药店\",\"220602\":\"店长\",\"220603\":\"执业药师/驻店药师\",\"220604\":\"店员/营业员\",\"220701\":\"市场营销/媒体\",\"220702\":\"医疗器械销售\",\"220703\":\"医学编辑\",\"220704\":\"医学总监\",\"220705\":\"药学编辑\",\"220706\":\"医药代表\",\"220707\":\"健康顾问\",\"220708\":\"医美咨询\",\"220801\":\"其他医疗健康类职位\",\"230101\":\"采购\",\"230102\":\"采购总监\",\"230103\":\"采购经理\",\"230104\":\"采购专员\",\"230105\":\"买手\",\"230106\":\"采购工程师\",\"230107\":\"采购主管\",\"230108\":\"采购助理\",\"230201\":\"进出口贸易\",\"230202\":\"外贸经理\",\"230203\":\"外贸专员\",\"230204\":\"外贸业务员\",\"230205\":\"贸易跟单\",\"230301\":\"其他采购/贸易类职位\",\"240101\":\"物流\",\"240102\":\"供应链专员\",\"240103\":\"供应链经理\",\"240104\":\"物流专员\",\"240105\":\"物流经理\",\"240106\":\"物流运营\",\"240107\":\"物流跟单\",\"240108\":\"贸易跟单\",\"240109\":\"物仓调度\",\"240110\":\"物仓项目\",\"240111\":\"运输经理/主管\",\"240112\":\"货运代理专员\",\"240113\":\"货运代理经理\",\"240114\":\"水/空/陆运操作\",\"240115\":\"报关员\",\"240116\":\"报检员\",\"240117\":\"核销员\",\"240118\":\"单证员\",\"240201\":\"仓储\",\"240202\":\"仓储物料经理\",\"240203\":\"仓储物料专员\",\"240204\":\"仓储物料项目\",\"240205\":\"仓储管理\",\"240206\":\"仓库文员\",\"240207\":\"配/理/拣/发货\",\"240301\":\"运输\",\"240302\":\"货运司机\",\"240303\":\"集装箱管理\",\"240304\":\"配送\",\"240305\":\"快递\",\"240401\":\"高端供应链职位\",\"240402\":\"供应链总监\",\"240403\":\"物流总监\",\"240501\":\"其他供应链职位\",\"250101\":\"房地产规划开发\",\"250102\":\"房产策划\",\"250103\":\"地产项目管理\",\"250104\":\"地产招投标\",\"250201\":\"设计装修与市政建设\",\"250202\":\"高级建筑工程师\",\"250203\":\"建筑工程师\",\"250204\":\"建筑设计师\",\"250205\":\"土木/土建/结构工程师\",\"250206\":\"室内设计\",\"250207\":\"园林设计\",\"250208\":\"城市规划设计\",\"250209\":\"工程监理\",\"250210\":\"工程造价\",\"250211\":\"预结算\",\"250212\":\"工程资料管理\",\"250213\":\"建筑施工现场管理\",\"250301\":\"房地产经纪\",\"250302\":\"地产置业顾问\",\"250303\":\"地产评估\",\"250304\":\"地产中介\",\"250401\":\"物业管理\",\"250402\":\"物业租赁销售\",\"250403\":\"物业招商管理\",\"250501\":\"高端房地产职位\",\"250502\":\"地产项目总监\",\"250503\":\"地产策划总监\",\"250504\":\"地产招投标总监\",\"250505\":\"物业总监\",\"250506\":\"房地产销售总监\",\"250601\":\"其他房地产职位\",\"260101\":\"咨询/调研\",\"260102\":\"企业管理咨询\",\"260103\":\"数据分析师\",\"260104\":\"财务咨询顾问\",\"260105\":\"IT咨询顾问\",\"260106\":\"人力资源顾问\",\"260107\":\"咨询项目管理\",\"260108\":\"战略咨询\",\"260109\":\"猎头顾问\",\"260110\":\"市场调研\",\"260111\":\"其他咨询顾问\",\"260201\":\"知识产权\",\"260202\":\"事务所律师\",\"260203\":\"公司法务\",\"260301\":\"英语翻译\",\"260302\":\"日语翻译\",\"260303\":\"韩语/朝鲜语翻译\",\"260304\":\"法语翻译\",\"260305\":\"德语翻译\",\"260306\":\"俄语翻译\",\"260307\":\"西班牙语翻译\",\"260308\":\"其他语种翻译\",\"260401\":\"高端咨询类职位\",\"260402\":\"咨询总监\",\"260403\":\"咨询经理\",\"260404\":\"高级翻译\",\"260405\":\"同声传译\",\"260501\":\"其他咨询/翻译类职位\",\"270101\":\"管理培训生\",\"270102\":\"储备干部\",\"270201\":\"其他实习/培训/储备职位\",\"280101\":\"旅游服务\",\"280102\":\"计调\",\"280103\":\"签证\",\"280104\":\"旅游顾问\",\"280105\":\"导游\",\"280106\":\"预定票务\",\"280201\":\"旅游产品开发/策划\",\"280202\":\"旅游产品经理\",\"280203\":\"旅游策划师\",\"280301\":\"其他旅游职位\",\"290101\":\"保安\",\"290102\":\"保洁\",\"290201\":\"酒店\",\"290202\":\"收银\",\"290203\":\"酒店前台\",\"290204\":\"客房服务员\",\"290205\":\"酒店经理\",\"290301\":\"后厨\",\"290302\":\"配菜打荷\",\"290303\":\"茶艺师\",\"290304\":\"西点师\",\"290305\":\"餐饮\",\"290306\":\"收银\",\"290307\":\"服务员\",\"290308\":\"厨师\",\"290309\":\"咖啡师\",\"290310\":\"送餐员\",\"290311\":\"餐饮店长\",\"290312\":\"领班\",\"290401\":\"督导/巡店\",\"290402\":\"陈列员\",\"290403\":\"理货员\",\"290404\":\"零售\",\"290405\":\"收银\",\"290406\":\"导购\",\"290407\":\"店员/营业员\",\"290408\":\"门店店长\",\"290501\":\"发型师\",\"290502\":\"美甲师\",\"290503\":\"化妆师\",\"290504\":\"健身\",\"290505\":\"瑜伽教练\",\"290506\":\"瘦身顾问\",\"290507\":\"游泳教练\",\"290508\":\"美体教练\",\"290509\":\"美容师/顾问\",\"290510\":\"舞蹈教练\",\"290511\":\"健身教练\",\"290601\":\"其他服务业职位\",\"300101\":\"生产营运\",\"300102\":\"厂长/工厂经理\",\"300103\":\"生产总监\",\"300104\":\"生产经理/车间主任\",\"300105\":\"生产组长/拉长\",\"300106\":\"生产员\",\"300107\":\"生产设备管理\",\"300108\":\"生产计划/物料控制\",\"300109\":\"生产跟单\",\"300201\":\"质量管理/测试\",\"300202\":\"可靠度工程师\",\"300203\":\"故障分析师\",\"300204\":\"认证工程师\",\"300205\":\"体系工程师\",\"300206\":\"审核员\",\"300207\":\"安全员\",\"300301\":\"机械设计/制造\",\"300302\":\"机械工程师\",\"300303\":\"机械设计师\",\"300304\":\"机械设备工程师\",\"300305\":\"机械维修/保养\",\"300306\":\"机械制图\",\"300307\":\"机械结构工程师\",\"300308\":\"工业工程师\",\"300309\":\"工艺/制程工程师\",\"300310\":\"材料工程师\",\"300311\":\"机电工程师\",\"300312\":\"CNC/数控\",\"300313\":\"冲压工程师\",\"300314\":\"夹具工程师\",\"300315\":\"模具工程师\",\"300316\":\"焊接工程师\",\"300317\":\"注塑工程师\",\"300318\":\"铸造/锻造工程师\",\"300401\":\"化工\",\"300402\":\"化工工程师\",\"300403\":\"实验室技术员\",\"300404\":\"化学分析\",\"300405\":\"涂料研发\",\"300406\":\"化妆品研发\",\"300407\":\"食品/饮料研发\",\"300501\":\"服装设计\",\"300502\":\"女装设计\",\"300503\":\"男装设计\",\"300504\":\"童装设计\",\"300505\":\"内衣设计\",\"300506\":\"面料设计\",\"300507\":\"面料辅料开发\",\"300508\":\"面料辅料采购\",\"300509\":\"打样/制版\",\"300510\":\"服装/纺织/皮革跟单\",\"300601\":\"普工/操作工\",\"300602\":\"叉车\",\"300603\":\"铲车\",\"300604\":\"焊工\",\"300605\":\"氩弧焊工\",\"300606\":\"电工\",\"300607\":\"木工\",\"300608\":\"漆工\",\"300609\":\"车工\",\"300610\":\"磨工\",\"300611\":\"铣工\",\"300612\":\"钳工\",\"300613\":\"钻工\",\"300614\":\"铆工\",\"300615\":\"钣金\",\"300616\":\"抛光\",\"300617\":\"机修工\",\"300618\":\"折弯工\",\"300619\":\"电镀工\",\"300620\":\"喷塑工\",\"300621\":\"注塑工\",\"300622\":\"组装工\",\"300623\":\"包装工\",\"300624\":\"空调工\",\"300625\":\"电梯工\",\"300626\":\"锅炉工\",\"300627\":\"学徒工\",\"300701\":\"其他生产制造职位\"}";

    private JSONObject jsonObject = null;

    {
        initJson();
    }

    /**
     * 通过id获取name
     */
    public String getNameById(Integer id) {
        String name = null;
        if(null == jsonObject){
            initJson();
        }

        if(jsonObject.containsKey(id.toString())){
            name = jsonObject.getString(id.toString());
        }
        return name;
    }

    /**
     * 初始化json
     */
    private void initJson() {
        try {
            jsonObject = JSONObject.parseObject(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}