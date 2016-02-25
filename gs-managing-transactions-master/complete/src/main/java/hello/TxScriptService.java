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

	/***
	 *
	 * @param persons
	 * @throws BizRuntimeException インターフェース契約違反のため、処理を完遂出来なかった。(spring-txの標準動作により)rollbackされる。
	 *                             ・パラメータがnull。(技術的例外)
	 *                               - いちいちパラメータがnullがどうかチェックして、nullなら処理結果として[パラメータがnullのため処理できませんでした]と返すようなことはしない。
	 *                               - パラメータが沢山あると膨大なコードを書くことになる。Simpleじゃない。
	 *                               Design by Contract(DbC).
	 *                               - 複雑さは品質の敵である。
	 *                               - パラメータがnullなのは、callerのバグ。過剰に防御するのではなく、誰の責任なのかをはっきりさせる。
	 *                             ・呼び出してはいけない状態で呼び出された。
	 *                               - あるべきマスターデータやファイル等が存在しなかった。(ビジネス例外)
	 *                             リカバリの可否はcaleeに判断不能。callerはリカバリ可能ならcatchし、しかるべき処理を。(ビジネス例外)
	 *                             不可能ならスルー(何もせず、servletなどの最上位のcatchにまかせそこでエラー画面へ遷移)すればよい。(技術的例外)
	 *                             技術的例外とビジネス例外。技術的例外は貫通させてフレームワークに任せる。ビジネス例外は準正常系なので呼び出し側(ユーザ/システム管理者)で対処する。
	 *                             分ける必要ある？Simpleじゃなくなる。ビジネス例外も貫通させても問題ないのでは？
	 * @throws
	 */
	@Transactional
	public ServiceResult execute(String... persons)
	{
		bookingService.book(persons);

		notifyService.notify(persons);

		// DbCに反する場合。
		throw new BizRuntimeException("マスターデータが存在しない。");
	}

	@Transactional
	public ServiceResult executeSuc(String... persons)
	{
		bookingService.book(persons);

		notifyService.notify(persons);

		// TODO: 業務エラーでrollbackさせたいときはどうする？ 業務エラーに例外を使うのはNGか?

		// 戻り値(Class型)で返す。
		return new ServiceResult();
	}
}
