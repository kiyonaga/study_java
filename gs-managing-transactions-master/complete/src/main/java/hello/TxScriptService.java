package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TxScriptService
{
	private final static Logger log = LoggerFactory.getLogger(TxScriptService.class);

	@Autowired
	private BookingService bookingService;
	@Autowired
	private NotifyService notifyService;

	// Pat. 2
	@Autowired
	private TxRollbackService rollbackService;

	// Pat. 3
	//	@Autowired
	//	private PlatformTransactionManager transactionManager;

	/***
	 *
	 * @param persons
	 * @throws BLogicRuntimeException インターフェース契約違反のため、処理を完遂出来なかった。(spring-txの標準動作により)rollbackされる。
	 * ・パラメータがnull。
	 *   - いちいちパラメータがnullがどうかチェックして、nullなら処理結果として[パラメータがnullのため処理できませんでした]と返すようなことはしない。
	 *   - パラメータが沢山あると膨大なコードを書くことになる。Simpleじゃない。
	 * ・呼び出してはいけない状態で呼び出された。
	 *   - あるべきマスターデータやファイル等が存在しなかった。外部の要因・責任。
	 *
	 * --- Design by Contract(DbC) ---
	 *   - 複雑さは品質の敵である。
	 *   - パラメータがnullなのは、callerのバグ。過剰に防御するのではなく、誰の責任なのかをはっきりさせる。
	 *   - (caleeは事前条件を明示する義務がある。どのマスタデータが必須なのか。)
	 * --- 技術的例外とビジネス例外 ---
	 *   - 技術的例外は貫通させてフレームワークに任せる。パラメータnull, あるべきマスタなし, SQLExceptionとか。
	 *   - ビジネス例外は準正常系なので呼び出し側で対処する。 <-- 準正常系なら例外を使わないに反する? ビジネス例外の定義が曖昧。
	 *                                                            エンドユーザが対処できる例外という定義か?ユーザ登録場面でIDが一意制約違反例外となってもエンドユーザが別IDにすることで対処できる場合など。
	 *   - エンドユーザが対処できないものは貫通させる。マスタ登録者やシステム管理者の対応が必要のものなど。
	 * リカバリの可否はcaleeに判断不能。callerはリカバリ可能ならcatchし、しかるべき処理を。(ビジネス例外)
	 * 不可能ならスルー(何もせず、servletなどの最上位のcatchにまかせ、そこでUIのエラーラベルに出力・ログ出力・エラー画面遷移等)すればよい。(技術的例外)
	 */
	@Transactional // Pat. 3の場合はco.
	public ServiceResult executeForceRollback(String... persons) throws BLogicRuntimeException
	{
		log.info("Rollback test...");

		// 途中で明示的にrollbackさせたいときはどうする？
		// - 分散トランザクション以外で、どのような状況で途中で明示的にrollbackしたいか。
		// - 業務エラーを処理の始めですべてチェックできれば、後はcommitだけに集中でき、途中で明示的にrollbackする必要はなくなる。
		// - 業務エラーを処理の途中でチェックする場面とは?
		//
		// 以下のPat.でrollbackできる。
		// 1. RuntimeExceptionをthrowする。エラーとなるのがイケてないか。
		// 2. rollback文を実行する。
		// 3. 明示的トランザクションを使用する。
		//
		// Pat. 2 がsimpleな感じ。 #ちょっとトリッキー感ある?

		// Pat. 3
		//		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		//		TransactionStatus status = transactionManager.getTransaction(def);

		// BizLogics
		bookingService.book(persons);
		notifyService.notify(persons);

		// Pat. 1
//				throw new BLogicRuntimeException("Intentional rollback.");

		// Pat. 2 rollback文
		rollbackService.rollback();

		// Pat. 3 明示的トランザクション
		//		transactionManager.rollback(status);

		return new ServiceResult("ForceRollback"); // Pat. 1の場合はco.
	}

	/***
	 *
	 * @param persons
	 * @return
	 */
	@Transactional
	public ServiceResult execute(String... persons)
	{
		ServiceResult ServiceResult = new ServiceResult();

		bookingService.book(persons);
		notifyService.notify(persons);

		// 戻り値、Class型で返す?
		return ServiceResult;
	}
}
