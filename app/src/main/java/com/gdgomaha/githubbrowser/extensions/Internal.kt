package com.gdgomaha.githubbrowser.extensions

import com.gdgomaha.githubbrowser.utils.DisposableManager
import io.reactivex.disposables.Disposable

operator fun DisposableManager.plus(disposable: Disposable) = add(disposable)
