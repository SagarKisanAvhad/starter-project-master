package com.mindorks.bootcamp.instagram.ui.dummy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mindorks.bootcamp.instagram.InstagramApplication
import com.mindorks.bootcamp.instagram.R
import com.mindorks.bootcamp.instagram.databinding.ActivityDummyBinding
import com.mindorks.bootcamp.instagram.di.component.DaggerActivityComponent
import com.mindorks.bootcamp.instagram.di.module.ActivityModule
import com.mindorks.bootcamp.instagram.ui.dummies.DummiesFragment
import javax.inject.Inject

class DummyActivity : AppCompatActivity() {

    companion object {
        const val TAG = "DummyActivity"
    }

    @Inject
    lateinit var viewModel: DummyViewModel

    lateinit var binding: ActivityDummyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dummy)
        binding.setLifecycleOwner(this)
        setupObservers()
        setupView()
        viewModel.onViewCreated()
    }

    private fun injectDependencies() {
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as InstagramApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }

    private fun setupObservers() {}

    private fun setupView() {
        addDummiesFragment()
    }

    fun addDummiesFragment() = supportFragmentManager
        .beginTransaction()
        .add(android.R.id.content, DummiesFragment.newInstance(), DummiesFragment.TAG)
        .commitAllowingStateLoss()
}