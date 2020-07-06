package com.sakibarai.login.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sakibarai.login.domain.model.SignupForm;
import com.sakibarai.login.domain.model.User;
import com.sakibarai.service.UserService;

// http://localhost:8080/signupに、
// GETメソッドでHTTPリクエストが来たら  → signup.html（ユーザー登録画面）に遷移する
// POSTメソッドでHTTPリクエストが来たら → ログイン画面(/login)にリダイレクトし、
//									  LoginControllerのGETメソッドを呼び出す

@Controller
public class SignupController {
	// ユーザー登録画面のGET用コントローラー
	// (GETメソッドでHTTPリクエストが来たら)
	// ---------------------------------------------------------------
	// @ModelAttribute (フォームクラスをModelに登録)
	@Autowired
	private UserService userService;//サービスクラスのinsertメソッドを呼び出す

	@GetMapping("/signup")
	public String getSignUp(@ModelAttribute SignupForm form) {
		//signup.htmlに画面遷移
		return "login/signup";
	}

	// ユーザー登録画面のPOST用コントローラー
	// (POSTメソッドでHTTPリクエストが来たら)
	@PostMapping("/signup")
	public String postSignUp(@ModelAttribute @Validated SignupForm form, BindingResult bindingResult,
			Model model) {
		// データバインド結果の受け取り(BindingResult)
		// ↓データバインド失敗の場合↓
		// 入力チェックに引っかかった場合、
		// ユーザー登録画面に戻る
		if(bindingResult.hasErrors()){
			//GETリクエスト用のメソッドを呼び出して、
			// ユーザー登録画面に戻る
			return getSignUp(form);
		}
		//---------------------------------------------------------------
		//insert用変数
		User user = new User();
		//ユーザーID
		user.setUserId(form.getUserId());
		//パスワード
		user.setPassword(form.getPassword());
		//ユーザー名
		user.setUserName(form.getUserName());
		// メールアドレス
		user.setMailAddress(form.getMailAddress());
		// アカウント権限
		user.setRole(form.getRole());
		//重複チェック
		boolean nameExists = userService.selectDuplicationName(user.getUserName());
		boolean mailExists = userService.selectDuplicationMail(user.getMailAddress());
		if(nameExists == true && mailExists == true){
			model.addAttribute("exists", "ユーザー名またはメールアドレスが重複しています");
			return getSignUp(form);
		}else if(nameExists == true){
			model.addAttribute("exists", "ユーザー名またはメールアドレスが重複しています");
			return getSignUp(form);
		}else if(mailExists == true){
			model.addAttribute("exists", "ユーザー名またはメールアドレスが重複しています");
			return getSignUp(form);
		}else{
			//ユーザー登録処理
			boolean result = userService.insert(user);
			//ユーザー登録結果の判定
			if(result == true){
				System.out.println("insert成功");
			}else{
				System.out.println("insert失敗");
			}
		}
		//---------------------------------------------------------------
		// login.htmlにリダイレクト
		// →http://localhost:8080/loginにGETメソッドでHTTPリクエストが送られる
		//   →LoginControllerのgetLoginメソッドが呼び出される
		return "redirect:/login";
	}

    //DataAccessException発生時の処理メソッド
    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHandler(DataAccessException e, Model model) {

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("error", "内部サーバーエラー（DB）：ExceptionHandler");

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("message", "SignupControllerでDataAccessExceptionが発生しました");

        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }

    //Exception発生時の処理メソッド
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("error", "内部サーバーエラー：ExceptionHandler");

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("message", "SignupControllerでExceptionが発生しました");

        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }
}

// リダイレクトする場合は、メソッドの返却値に
// 『redirect:<遷移先パス>』と指定する
// リダイレクトすると、遷移先のControllerクラスのメソッドが呼ばれる
// (今回の場合、http://localhost:8080/loginにGETメソッドでHTTPリクエストが送られる
// そして、LoginControllerのgetLoginメソッドが呼び出される)
//---------------------------------------------------------------
// @ModelAttribute
// 引数のフォームクラスに@ModelAttributeアノテーションを付けると、
// 自動でModelクラスに登録（addAttribute）される
// 【イメージ】
//@GetMapping("/signup")
//public String getSignUp(SignupForm form, Model model){
//	model.addAttribute("SignupForm",form);	フォームクラスをModelに登録
//	return"login/signup";			login.htmlに画面遷移
//}
// @ModelAttributeを付けた場合、
// デフォルトではクラス名の最初の文字を小文字に変えた文字列が、キー名に登録される
// 今回の場合でいうと、"signupForm"というキー名で登録されている
// キー名を変えたい場合は、@ModelAttribute("<キー名>")と、パラメーターを指定する
//---------------------------------------------------------------
//･DataAccessException
//Springでは、データベース操作で例外が発生した場合、
//Springが提供しているDataAccessExceptionを投げる
//---------------------------------------------------------------
//バリデーションを実施するには、引数のフォームクラスに@Validatedアノテーションを付ける
//また、バリデーションのチェック結果はBindingResultクラスに入っている
//そのため、バリデーションを使う場合でもBindingResultクラスを引数に設定する必要がある
