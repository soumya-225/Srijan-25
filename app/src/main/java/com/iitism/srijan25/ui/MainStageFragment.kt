package com.iitism.srijan25.ui

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.iitism.srijan25.R
import com.iitism.srijan25.adapter.ImagePagerAdapter
import com.iitism.srijan25.databinding.FragmentMainStageBinding
import kotlin.math.abs


class MainStageFragment : Fragment() {

    private var _binding: FragmentMainStageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainStageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val imageUrls2024 =
            listOf("https://res.cloudinary.com/dimf24hn7/image/upload/v1727122789/coming_soon_cljlt7.png")
        val imageUrls2023 = listOf(
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727112098/Here_are_some_glimpses_of_EDM_night_conducted_under__concetto.iitism_2023._._._Ft.__progressivebrothers____kavyakhurana_._.__edmnight__edm__probros__sunburn__sunburncampus__kavyakhurana__iitism__2_l1ms2v.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727112100/IIT_Dhanbad_was_phenomenal._%EF%B8%8F_._Wearing__thesouledstore__._Managed_by__the_hustler_kind__._Amazing_work_behind_the_cameras_by__vaibhav_007___and__fotofreaks_iitism____concetto.iitism__tribevi_4_ucyh7j.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727112096/A_night_filled_with_laughter_is_a_night_well_spent___._So_here_are_some_glimpses_of_Comedy_night_by__gauravkpoor_opened_by__ankitbharadwaj10_organised_during_Concetto_23._._.__comedian__comedyni_2_ncmjij.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125582/As_the_Concetto_23_comes_to_an_end_here_are_some_of_the_glimpses_part-1_of_the_Techno-Management_Fest_from_our_side._._.__concetto.iitism___iit.ism__._.__concetto__fotofreaks_iitism__iitism__co_cplzi2.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125582/As_the_Concetto_23_comes_to_an_end_here_are_some_of_the_glimpses_part-1_of_the_Techno-Management_Fest_from_our_side._._.__concetto.iitism___iit.ism__._.__concetto__fotofreaks_iitism__iitism__c_1_tpgs3z.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125582/As_the_Concetto_23_comes_to_an_end_here_are_some_of_the_glimpses_part-1_of_the_Techno-Management_Fest_from_our_side._._.__concetto.iitism___iit.ism__._.__concetto__fotofreaks_iitism__iitism__c_2_xfruuo.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125583/As_the_Concetto_23_comes_to_an_end_here_are_some_of_the_glimpses_Part-2_of_the_Techno-Management_fest_from_our_side._._.__concetto.iitism___iit.ism__._.__concetto__fotofreaks_iitism__iitism__co_bq4gjc.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125583/As_the_Concetto_23_comes_to_an_end_here_are_some_of_the_glimpses_Part-2_of_the_Techno-Management_fest_from_our_side._._.__concetto.iitism___iit.ism__._.__concetto__fotofreaks_iitism__iitism__c_1_h1laah.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727112099/Here_are_some_glimpses_of_EDM_night_conducted_under__concetto.iitism_2023._._._Ft.__progressivebrothers____kavyakhurana_._.__edmnight__edm__probros__sunburn__sunburncampus__kavyakhurana__iitism__7_xmyfpd.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727112097/A_short_glimpse_of_the_Cult_Night_organised_under_Concetto_23.__._._Dance_Performance_by-__legends_iitdhanbad_and__wtc_society__._.__fotofreaks_iitism__iitism__litm__wtc__dance__cultnight__concet_5_v1pnfn.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727112100/IIT_Dhanbad_was_phenomenal._%EF%B8%8F_._Wearing__thesouledstore__._Managed_by__the_hustler_kind__._Amazing_work_behind_the_cameras_by__vaibhav_007___and__fotofreaks_iitism____concetto.iitism__tribevi_2_kbrbw3.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727112096/A_short_glimpse_of_Magic_Show_performed_by__magicboy_kabir_organised_under_Concetto_23._._.__fotofreaks_iitism__iitism__concetto__magicshow__kabirsharma__abracadabra__collegefest__fest_0_JPG_npmzlq.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727112098/Here_are_some_glimpses_of_EDM_night_conducted_under__concetto.iitism_2023._._._Ft.__progressivebrothers____kavyakhurana_._.__edmnight__edm__probros__sunburn__sunburncampus__kavyakhurana__iitism__3_jjwisf.jpg"

        )
        val imageUrls2022 = listOf(
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125582/A_short_glimpse_of_the_EDM_night_held_at_Amber_ground_which_marked_the_ending_of_Concetto_22_-_The_Annual_Techno_Management_Festival_of_IIT_ISM_Dhanbad._.__swattrexmusic___djmerlin_official___con_uz7zwt.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125581/A_short_glimpse_of_the_EDM_night_held_at_Amber_ground_which_marked_the_ending_of_Concetto_22_-_The_Annual_Techno_Management_Festival_of_IIT_ISM_Dhanbad._.__swattrexmusic___djmerlin_official___co_1_vk5i83.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125581/A_short_glimpse_of_Day_3_events_at_Concetto_s_22._.___concetto.iitism__iit.ism___._._fotofreaks_iitism__concetto__techfest__technology__robots__engineering_fun__collegelifestyle__collegefest__sci_fasqbh.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125581/With_Robowars__Tech-_talks__motivational_speeches__competitions__and_dance_night__day_2_of_Concetto__22_ended_on_a_high_note._Here_is_a_glimpse_of_the_happenings_around_the_campus._._.__concett_1_dycykd.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125581/A_short_glimpse_of_Day_1_events_at_Concetto__22._._.__concetto.iitism__iit.ism__._.__fotofreaks_iitism__concetto__iitism__techfestival__festival__technology__robots__robowar__techfreak__realitybe_teuzgw.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125581/With_Robowars__Tech-_talks__motivational_speeches__competitions__and_dance_night__day_2_of_Concetto__22_ended_on_a_high_note._Here_is_a_glimpse_of_the_happenings_around_the_campus._._.__concetto_shp304.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125582/A_short_glimpse_of_the_EDM_night_held_at_Amber_ground_which_marked_the_ending_of_Concetto_22_-_The_Annual_Techno_Management_Festival_of_IIT_ISM_Dhanbad._.__swattrexmusic___djmerlin_official___co_2_ztgndq.jpg"
        )

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((40 * Resources.getSystem().displayMetrics.density).toInt()))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.80f + r * 0.20f)
        }
        binding.viewPager2022.setPageTransformer(compositePageTransformer)
        binding.viewPager2023.setPageTransformer(compositePageTransformer)
        binding.viewPager2024.setPageTransformer(compositePageTransformer)

        val viewPager2024 = view.findViewById<ViewPager2>(R.id.viewPager2024)
        val adapter2024 = ImagePagerAdapter(imageUrls2024, this)
        viewPager2024.adapter = adapter2024

        val viewPager2023 = view.findViewById<ViewPager2>(R.id.viewPager2023)
        val adapter2023 = ImagePagerAdapter(imageUrls2023, this)
        viewPager2023.adapter = adapter2023

        val viewPager2022 = view.findViewById<ViewPager2>(R.id.viewPager2022)
        val adapter2022 = ImagePagerAdapter(imageUrls2022, this)
        viewPager2022.adapter = adapter2022


        addDotsIndicator(imageUrls2022.size,binding.dotLayout2022)
        binding.viewPager2022.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateDots(position,binding.dotLayout2022)
            }
        })
        addDotsIndicator(imageUrls2023.size,binding.dotLayout2023)
        binding.viewPager2023.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateDots(position,binding.dotLayout2023)
            }
        })
        addDotsIndicator(imageUrls2024.size,binding.dotLayout2024)
        binding.viewPager2024.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateDots(position,binding.dotLayout2024)
            }
        })



//        val dotsIndicator2024 = view.findViewById<WormDotsIndicator>(R.id.dotsIndicator2024)
//        dotsIndicator2024.setViewPager2(viewPager2024)

//
//        // Set up ViewPager for 2023
//
//

//        // Set up dots indicators for 2023
//        val dotsIndicator2023 = view.findViewById<WormDotsIndicator>(R.id.dotsIndicator2023)
//        dotsIndicator2023.setViewPager2(viewPager2023)
//
//// Set up dots indicators for 2022
//        val dotsIndicator2022 = view.findViewById<WormDotsIndicator>(R.id.dotsIndicator2022)
//        dotsIndicator2022.setViewPager2(viewPager2022)

    }

    private fun addDotsIndicator(size: Int,dotLayout: LinearLayout) {
        val dots = arrayOfNulls<ImageView>(size)
        for (i in 0 until size) {
            dots[i] = ImageView(requireContext())
            dots[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.indicator_inactive
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            dotLayout.addView(dots[i], params)
        }
        dots[0]?.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.indicator_active
            )
        )
    }

    private fun updateDots(position: Int,dotLayout: LinearLayout) {
        val childCount = dotLayout.childCount
        for (i in 0 until childCount) {
            val dot = dotLayout.getChildAt(i) as ImageView
            val drawableId =
                if (i == position) R.drawable.indicator_active else R.drawable.indicator_inactive
            dot.setImageDrawable(ContextCompat.getDrawable(requireContext(), drawableId))
        }
    }

}