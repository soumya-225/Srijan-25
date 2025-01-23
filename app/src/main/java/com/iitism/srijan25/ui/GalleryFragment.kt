package com.iitism.srijan25.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.iitism.srijan25.R
import com.iitism.srijan25.adapter.GalleryAdapter

class GalleryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        val navController = findNavController()

        val recyclerView = view.findViewById<RecyclerView>(R.id.galleryRecyclerView)
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)

        toolbar.setNavigationOnClickListener {
            navController.navigateUp()
        }

        val imageUrls = listOf(
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383494/These_are_a_few_glances_of_thundering_EDM_night_organised_under_Srijan_23._._Featuring__vanmoonmusic__tracermusicofficial__ninasuerte_._.__fotofreaks_iitis_gfs6k5.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383494/These_are_a_few_glances_of_thundering_EDM_night_organised_under_Srijan_23._._Featuring__vanmoonmusic__tracermusicofficial__ninasuerte_._.__fotofreaks_iiti_2_zqvpxb.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383478/Ready_to_make_a_stellar_statement_among_your_friends__Don_the_official_merchandise_for_Srijan__24_and_secure_your_ticket_to_the_Cultural_Caravan__1_anqpjs.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383476/Part_1_Immersed_in_the_captivating_melodies_of_love_and_soul__alongside_the_revered_Professor_of_Love__Shaan_Mukherji__the_night_became_an_unforgettable_1_rfm5zr.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383476/Part_2_The_night_echoes_with_the_melodies_and_memories_from_Srijan_s_Pro-Night_with__nikhilmusic_and_opened_by__vaanibhasin___srijan__cultfest__fest__mu_9_k4jcx6.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383494/These_are_a_few_glances_of_thundering_EDM_night_organised_under_Srijan_23._._Featuring__vanmoonmusic__tracermusicofficial__ninasuerte_._.__fotofreaks_iiti_6_gicn2h.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383494/These_are_a_few_glances_of_thundering_EDM_night_organised_under_Srijan_23._._Featuring__vanmoonmusic__tracermusicofficial__ninasuerte_._.__fotofreaks_iiti_3_jgl9dl.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383493/IIT_Dhanbad__I_had_a_fabulous_time_singing_with_4000_of_you.___Tags___vaanibhasin__liveperformance__iitdhanbad_JPG__1_szk8bn.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383489/A_short_glimpse_of_Day_3_events_in_Srijan_2023._._.__fotofreaks_iitism__iitism__culturals_iitism__srijan_WEBP__4_vffpie.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383475/Part_1_Immersed_in_the_captivating_melodies_of_love_and_soul__alongside_the_revered_Professor_of_Love__Shaan_Mukherji__the_night_became_an_unforgettable_6_wl27d3.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383476/PARIDHAAN_-_A_showcase_of_Fashion_Trend_sponsored_by__beardo.official_._.__fotofreaks_iitism__srijan2023__iitism__iit__paridhaan_WEBP__2_yb5sox.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383478/IIT_Dhanbad__I_had_a_fabulous_time_singing_with_4000_of_you.___Tags___vaanibhasin__liveperformance__iitdhanbad_JPG__6_d878v7.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383476/Part_2_The_night_echoes_with_the_melodies_and_memories_from_Srijan_s_Pro-Night_with__nikhilmusic_and_opened_by__vaanibhasin___srijan__cultfest__fest__mu_1_hcp108.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383478/Ready_to_make_a_stellar_statement_among_your_friends__Don_the_official_merchandise_for_Srijan__24_and_secure_your_ticket_to_the_Cultural_Caravan__2_r8w3za.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383478/The_most_awaited_college_experience_just_happened___Here_are_the_glimpses_of_the_dazzling_3_days_of_Srijan__the_carnival_of_reminiscence_which_took_all_of_7_jpsbln.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383478/The_most_awaited_college_experience_just_happened___Here_are_the_glimpses_of_the_dazzling_3_days_of_Srijan__the_carnival_of_reminiscence_which_took_all_of_3_abpsrh.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383479/These_are_a_few_glances_of_thundering_EDM_night_organised_under_Srijan_23._._Featuring__vanmoonmusic__tracermusicofficial__ninasuerte_._.__fotofreaks_iiti_4_b1gxuc.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383478/IIT_Dhanbad__I_had_a_fabulous_time_singing_with_4000_of_you.___Tags___vaanibhasin__liveperformance__iitdhanbad_JPG__5_ymxvdk.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383478/IIT_Dhanbad__I_had_a_fabulous_time_singing_with_4000_of_you.___Tags___vaanibhasin__liveperformance__iitdhanbad_JPG__2_wkcyz9.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383478/A_short_glimpse_of_Day_2_events_in_Srijan_2023._._.__fotofreaks_iitism__iitism__culturals_iitism__srijan_JPG__7_wv3qyu.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383477/A_short_glimpse_of_Day_2_events_in_Srijan_2023._._.__fotofreaks_iitism__iitism__culturals_iitism__srijan_JPG__6_rjmeid.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383477/Part_2_The_night_echoes_with_the_melodies_and_memories_from_Srijan_s_Pro-Night_with__nikhilmusic_and_opened_by__vaanibhasin___srijan__cultfest__fest__mus_kyrl3u.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383477/A_short_glimpse_of_Day_2_events_in_Srijan_2023._._.__fotofreaks_iitism__iitism__culturals_iitism__srijan_JPG_zgrcsu.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383477/Part_2_The_night_echoes_with_the_melodies_and_memories_from_Srijan_s_Pro-Night_with__nikhilmusic_and_opened_by__vaanibhasin___srijan__cultfest__fest__mu_7_qknuxk.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383477/Part_2_The_night_echoes_with_the_melodies_and_memories_from_Srijan_s_Pro-Night_with__nikhilmusic_and_opened_by__vaanibhasin___srijan__cultfest__fest__mu_8_l2augm.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383476/Part_2_The_night_echoes_with_the_melodies_and_memories_from_Srijan_s_Pro-Night_with__nikhilmusic_and_opened_by__vaanibhasin___srijan__cultfest__fest__mu_9_k4jcx6.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383476/Part_2_The_night_echoes_with_the_melodies_and_memories_from_Srijan_s_Pro-Night_with__nikhilmusic_and_opened_by__vaanibhasin___srijan__cultfest__fest__mu_5_omlhva.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383476/Part_2_The_night_echoes_with_the_melodies_and_memories_from_Srijan_s_Pro-Night_with__nikhilmusic_and_opened_by__vaanibhasin___srijan__cultfest__fest__mu_6_ljqyci.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383476/Part_2_The_night_echoes_with_the_melodies_and_memories_from_Srijan_s_Pro-Night_with__nikhilmusic_and_opened_by__vaanibhasin___srijan__cultfest__fest__mu_1_hcp108.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383476/Part_1_Immersed_in_the_captivating_melodies_of_love_and_soul__alongside_the_revered_Professor_of_Love__Shaan_Mukherji__the_night_became_an_unforgettable_1_rfm5zr.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383476/PARIDHAAN_-_A_showcase_of_Fashion_Trend_sponsored_by__beardo.official_._.__fotofreaks_iitism__srijan2023__iitism__iit__paridhaan_WEBP__2_yb5sox.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383475/The_most_awaited_college_experience_just_happened___Here_are_the_glimpses_of_the_dazzling_3_days_of_Srijan__the_carnival_of_reminiscence_which_took_all_of_9_ehciqr.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383475/Part_2_The_night_echoes_with_the_melodies_and_memories_from_Srijan_s_Pro-Night_with__nikhilmusic_and_opened_by__vaanibhasin___srijan__cultfest__fest__mu_3_yuahjp.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383475/Part_2_The_night_echoes_with_the_melodies_and_memories_from_Srijan_s_Pro-Night_with__nikhilmusic_and_opened_by__vaanibhasin___srijan__cultfest__fest__mu_2_xvhy1w.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383475/The_most_awaited_college_experience_just_happened___Here_are_the_glimpses_of_the_dazzling_3_days_of_Srijan__the_carnival_of_reminiscence_which_took_all_of_8_sxkgau.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383475/Part_1_Immersed_in_the_captivating_melodies_of_love_and_soul__alongside_the_revered_Professor_of_Love__Shaan_Mukherji__the_night_became_an_unforgettable_6_wl27d3.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383474/The_most_awaited_college_experience_just_happened___Here_are_the_glimpses_of_the_dazzling_3_days_of_Srijan__the_carnival_of_reminiscence_which_took_all_of_6_sc6xkc.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383474/The_most_awaited_college_experience_just_happened___Here_are_the_glimpses_of_the_dazzling_3_days_of_Srijan__the_carnival_of_reminiscence_which_took_all_of_4_fjqcju.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383474/Ready_to_make_a_stellar_statement_among_your_friends__Don_the_official_merchandise_for_Srijan__24_and_secure_your_ticket_to_the_Cultural_Caravan__rltdxm.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383474/The_most_awaited_college_experience_just_happened___Here_are_the_glimpses_of_the_dazzling_3_days_of_Srijan__the_carnival_of_reminiscence_which_took_all_of_2_jhcasc.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383474/The_most_awaited_college_experience_just_happened___Here_are_the_glimpses_of_the_dazzling_3_days_of_Srijan__the_carnival_of_reminiscence_which_took_all_of_5_jsaeoo.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383472/A_short_glimpse_of_Day_3_events_in_Srijan_2023._._.__fotofreaks_iitism__iitism__culturals_iitism__srijan_WEBP__5_hawev6.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383472/A_short_glimpse_of_Day_2_events_in_Srijan_2023._._.__fotofreaks_iitism__iitism__culturals_iitism__srijan_JPG__5_gagfk2.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383473/PARIDHAAN_-_A_showcase_of_Fashion_Trend_sponsored_by__beardo.official_._.__fotofreaks_iitism__srijan2023__iitism__iit__paridhaan_WEBP__3_rys7ma.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383473/The_most_awaited_college_experience_just_happened___Here_are_the_glimpses_of_the_dazzling_3_days_of_Srijan__the_carnival_of_reminiscence_which_took_all_of_d2qdvd.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383473/PARIDHAAN_-_A_showcase_of_Fashion_Trend_sponsored_by__beardo.official_._.__fotofreaks_iitism__srijan2023__iitism__iit__paridhaan_WEBP__4_zgppdg.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383472/A_short_glimpse_of_Day_3_events_in_Srijan_2023._._.__fotofreaks_iitism__iitism__culturals_iitism__srijan_WEBP_t6k4em.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383472/A_short_glimpse_of_Day_2_events_in_Srijan_2023._._.__fotofreaks_iitism__iitism__culturals_iitism__srijan_JPG__3_eufb7m.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383471/Part_1_Immersed_in_the_captivating_melodies_of_love_and_soul__alongside_the_revered_Professor_of_Love__Shaan_Mukherji__the_night_became_an_unforgettable_9_carvus.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383471/Part_1_Immersed_in_the_captivating_melodies_of_love_and_soul__alongside_the_revered_Professor_of_Love__Shaan_Mukherji__the_night_became_an_unforgettable_4_gdhmki.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383471/A_short_glimpse_of_Day_2_events_in_Srijan_2023._._.__fotofreaks_iitism__iitism__culturals_iitism__srijan_JPG__8_v9dblc.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383470/A_short_glimpse_of_Day_3_events_in_Srijan_2023._._.__fotofreaks_iitism__iitism__culturals_iitism__srijan_WEBP__2_lqznx8.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383470/A_short_glimpse_of_Day_2_events_in_Srijan_2023._._.__fotofreaks_iitism__iitism__culturals_iitism__srijan_JPG__2_mstg3o.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1737383469/A_short_glimpse_of_Day_2_events_in_Srijan_2023._._.__fotofreaks_iitism__iitism__culturals_iitism__srijan_JPG__1_ijzk2z.jpg"

        )

        recyclerView.adapter = GalleryAdapter(imageUrls,navController)
        recyclerView.layoutManager = GridLayoutManager(context, 2) // 2 columns
        return view
    }
}
