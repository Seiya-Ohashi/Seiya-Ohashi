package com.sakibarai;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//セキュリティ設定用クラス
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	//パスワードエンコーダーのBean定義
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	//データソース
	@Autowired
	private DataSource dataSource;
	//ユーザーIDとパスワードを取得するSQL文
	private static final String USER_SQL = "SELECT"
			+ " mail_address,"
			+ " password,"
			+ " true"
			+ " FROM"
			+ " user_info"
			+ " WHERE"
			+ " mail_address = ?";
	//ユーザーのflagを取得するSQL文
	private static final String FLAG_SQL = "SELECT"
			+ " mail_address,"
			+ " user_category_flag"
			+ " FROM"
			+ " user_info"
			+ " WHERE"
			+ " mail_address = ?";
	@Override
	public void configure(WebSecurity web) throws Exception {
		//静的リソースを除外
		//静的リソースへのアクセスには、セキュリティを適用しない
		web.ignoring().antMatchers("/webjars/**", "/css/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//直リンクの禁止
		//ログイン不要ページの設定
		http
				.authorizeRequests()
				.antMatchers("/webjars/**").permitAll()//webjarsへアクセス許可
				.antMatchers("/css/**").permitAll()//cssへアクセス許可
				.antMatchers("/login").permitAll()//ログインページは直リンクOK        
				.antMatchers("/signup").permitAll()//ユーザー登録画面は直リンクOK        
				.anyRequest().authenticated();//それ以外は直リンク禁止

		//ログイン処理
		http.formLogin()
				.loginProcessingUrl("/login")//ログイン処理のパス
				.loginPage("/login")//ログインページの指定
				.failureUrl("/login")//ログイン失敗時の遷移先
				.usernameParameter("mailAddress")//ログインページのメールアドレス
				.passwordParameter("password")//ログインページのパスワード
				.defaultSuccessUrl("/home", true);//ログイン成功後の遷移先

		//ログアウト処理
		http
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutUrl("/logout")
		.logoutSuccessUrl("/login");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//ポイント：ユーザーデータの取得（DB）
		//ログイン処理時のユーザー情報を、DBから取得する
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(USER_SQL)
				.authoritiesByUsernameQuery(FLAG_SQL)
				.passwordEncoder(passwordEncoder());//ログイン時のパスワード復号
	}
}

//･セキュリティ設定用クラス
//セキュリティ設定用クラスには、@EnableWebSecurityを付ける
//また、WebSecurityConfigurerAdapterクラスを継承する
//このクラスの各種メソッドをオーバーライドすることで、
//セキュリティの設定を行っていくことができる
//セキュリティ用にBean定義を行うこともあるため、
//@Configurationアノテーションを付ける

//･静的リソースを除外
//webjarsやcssなどの静的リソースには、誰でもアクセスできるようにする
//上記コードのように設定することで、セキュリティ設定を適用しないようにする

//･直リンクの禁止
//直リンクを禁止するためには、
//http.authorizeRequests()にメソッドチェーンでリンク禁止先の条件を追加していく
//メソッドチェーン→ドット(.)でメソッドを連続して呼び出すこと

//・antMatchers("<リンク先>").permitAll()
//antMatchersメソッドの引数に、リンク先をセットすることで、
//そのリンク先に対する設定をすることができる
//そのリンク先にpermitAllメソッドを使うことで、
//認証（ログイン）してないユーザーでもリンク先にアクセスすることができる
//このように設定したリンク先は、直リンクができる

//・anyRequest().authenticated()
//anyRequestメソッドで、全てのリンク先が対象になる
//そして、authenticatedメソッドで、認証しないとアクセスできないように設定し、
//permitAllしたリンク先以外の直リンクを禁止する
//-------------------------------------------------------------------
//･ログイン処理
//ログイン処理を追加する為、http.formLogin()にメソッドチェーンで条件を追加していく

//・loginProcessingUrl("<リンク先>")
//ログイン処理をするURLを指定
//ログイン画面のhtmlにあるフォームタグのaction="/login"の部分と一致させる

//・loginPage("<リンク先>")
//自分で作成したログイン画面を表示させる為にリンク先を設定する
//ログイン画面のコントローラークラスにある@GetMapping("/login")の部分に一致させる

//・failureUrl("<リンク先>")
//ログインが失敗した場合の遷移先

//・userNameParameter("<パラメーター名>")
//ログイン画面のメールアドレス入力エリアのパラメーター名を指定
//ユーザーデータの取得（DB）で使用する
//指定するパラメータ名は、htmlファイルから探す(今回はlogin.html)
//具体的には、メールアドレスを入力する以下の箇所
//【login.html】
//<label>メールアドレス</label><input type="text"name="∗{mailAddress}"/><br/>
//このname属性に設定されているmailAdress文字列を、
//SecurityConfigのuserNameParameter("<パラメーター名>")にセット

//・passwordParameter("<パラメーター名>")
//userNameParameterと同様、ログインページでパスワード入力エリアのパラメーター名を指定
//-------------------------------------------------------------------
//･ユーザーデータの取得（DB）
//SQLを実行できるようにするため、DataSourceをAutowiredする
//次に、ユーザーデータを取得するために、SQL文を２つ用意する
//1つ目のSQL文では、メールアドレス、パスワード、使用可否（Enabled）を検索
//2つ目のSQL文では、権限を検索
//これらのSQL文を、
//usersByUsernameQueryメソッドと
//authoritiesByUsernameQueryメソッドの引数に入れる
//これで、入力されたメールアドレスとパスワードを使って、認証処理をSpringが行ってくれる
//次→パスワードの暗号化を実装
//-------------------------------------------------------------------
//･パスワードエンコーダーのBean定義
//パスワードを暗号化したり、復号するインターフェースとして、
//PasswordEncoderというインターフェースがSpringで用意されている
//そのPasswordEncoderを実装した、BCryptPasswordEncoderのインスタンスを返すBean定義をしている
//Bean定義しているのは、ユーザー登録のリポジトリークラスなどで使う（Autowiredする）ため
//PasswordEncoderを実装したクラスは、BCryptPasswordEncoder以外にもいくつかあり、
//暗号化のアルゴリズムによって、実装クラスが分かれている

//･ログイン時のパスワード復号
//ログイン処理の際に、パスワードを復号するために、
//passwordEncoderメソッドにBean定義したPasswordEncoderをセット
//これで、ログイン時にパスワードをSpringが復号してくれる