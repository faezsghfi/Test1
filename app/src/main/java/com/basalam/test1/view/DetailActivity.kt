package com.basalam.test1.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.basalam.test1.R
import com.basalam.test1.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import com.ouattararomuald.slider.SliderAdapter
import com.ouattararomuald.slider.loaders.glide.GlideImageLoaderFactory


@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id : Int = this.intent.getIntExtra("id",0)
        val animalName : String? = this.intent.getStringExtra("animalName")
        val flowerName : String? = this.intent.getStringExtra("flowerName")
        val animalImage : String? = this.intent.getStringExtra("animalImage")
        val flowerImage : String? = this.intent.getStringExtra("flowerImage")
        val common : String? = this.intent.getStringExtra("common")


        val face = ResourcesCompat.getFont(this, R.font.sans )

        val imageUrls = arrayListOf(animalImage!!,flowerImage!!)

        binding.detailNames.text = "$animalName / $flowerName"
        binding.detailId.text = id.toString()
        binding.detailCommon.text = "حروف مشترک : " + common.toString()

        binding.detailNames.typeface = face
        binding.detailId.typeface = face
        binding.detailCommon.typeface = face

        binding.imageSlider.adapter = SliderAdapter(
            this,
            GlideImageLoaderFactory(),
            imageUrls = imageUrls
        )
        binding.detailToolbarBack.setOnClickListener {
            finish()
        }
        }

    }
