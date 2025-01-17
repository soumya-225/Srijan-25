package com.iitism.srijan25.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        val imageUrls = listOf(
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125582/A_short_glimpse_of_the_EDM_night_held_at_Amber_ground_which_marked_the_ending_of_Concetto_22_-_The_Annual_Techno_Management_Festival_of_IIT_ISM_Dhanbad._.__swattrexmusic___djmerlin_official___con_uz7zwt.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727112098/Here_are_some_glimpses_of_EDM_night_conducted_under__concetto.iitism_2023._._._Ft.__progressivebrothers____kavyakhurana_._.__edmnight__edm__probros__sunburn__sunburncampus__kavyakhurana__iitism__2_l1ms2v.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727112100/IIT_Dhanbad_was_phenomenal._%EF%B8%8F_._Wearing__thesouledstore__._Managed_by__the_hustler_kind__._Amazing_work_behind_the_cameras_by__vaibhav_007___and__fotofreaks_iitism____concetto.iitism__tribevi_4_ucyh7j.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727112096/A_night_filled_with_laughter_is_a_night_well_spent___._So_here_are_some_glimpses_of_Comedy_night_by__gauravkpoor_opened_by__ankitbharadwaj10_organised_during_Concetto_23._._.__comedian__comedyni_2_ncmjij.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125581/A_short_glimpse_of_the_EDM_night_held_at_Amber_ground_which_marked_the_ending_of_Concetto_22_-_The_Annual_Techno_Management_Festival_of_IIT_ISM_Dhanbad._.__swattrexmusic___djmerlin_official___co_1_vk5i83.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125581/A_short_glimpse_of_Day_3_events_at_Concetto_s_22._.___concetto.iitism__iit.ism___._._fotofreaks_iitism__concetto__techfest__technology__robots__engineering_fun__collegelifestyle__collegefest__sci_fasqbh.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125582/As_the_Concetto_23_comes_to_an_end_here_are_some_of_the_glimpses_part-1_of_the_Techno-Management_Fest_from_our_side._._.__concetto.iitism___iit.ism__._.__concetto__fotofreaks_iitism__iitism__co_cplzi2.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125582/As_the_Concetto_23_comes_to_an_end_here_are_some_of_the_glimpses_part-1_of_the_Techno-Management_Fest_from_our_side._._.__concetto.iitism___iit.ism__._.__concetto__fotofreaks_iitism__iitism__c_1_tpgs3z.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125582/As_the_Concetto_23_comes_to_an_end_here_are_some_of_the_glimpses_part-1_of_the_Techno-Management_Fest_from_our_side._._.__concetto.iitism___iit.ism__._.__concetto__fotofreaks_iitism__iitism__c_2_xfruuo.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125581/With_Robowars__Tech-_talks__motivational_speeches__competitions__and_dance_night__day_2_of_Concetto__22_ended_on_a_high_note._Here_is_a_glimpse_of_the_happenings_around_the_campus._._.__concett_1_dycykd.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125581/A_short_glimpse_of_Day_1_events_at_Concetto__22._._.__concetto.iitism__iit.ism__._.__fotofreaks_iitism__concetto__iitism__techfestival__festival__technology__robots__robowar__techfreak__realitybe_teuzgw.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125581/With_Robowars__Tech-_talks__motivational_speeches__competitions__and_dance_night__day_2_of_Concetto__22_ended_on_a_high_note._Here_is_a_glimpse_of_the_happenings_around_the_campus._._.__concetto_shp304.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125582/A_short_glimpse_of_the_EDM_night_held_at_Amber_ground_which_marked_the_ending_of_Concetto_22_-_The_Annual_Techno_Management_Festival_of_IIT_ISM_Dhanbad._.__swattrexmusic___djmerlin_official___co_2_ztgndq.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125583/As_the_Concetto_23_comes_to_an_end_here_are_some_of_the_glimpses_Part-2_of_the_Techno-Management_fest_from_our_side._._.__concetto.iitism___iit.ism__._.__concetto__fotofreaks_iitism__iitism__co_bq4gjc.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727125583/As_the_Concetto_23_comes_to_an_end_here_are_some_of_the_glimpses_Part-2_of_the_Techno-Management_fest_from_our_side._._.__concetto.iitism___iit.ism__._.__concetto__fotofreaks_iitism__iitism__c_1_h1laah.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727112099/Here_are_some_glimpses_of_EDM_night_conducted_under__concetto.iitism_2023._._._Ft.__progressivebrothers____kavyakhurana_._.__edmnight__edm__probros__sunburn__sunburncampus__kavyakhurana__iitism__7_xmyfpd.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727112097/A_short_glimpse_of_the_Cult_Night_organised_under_Concetto_23.__._._Dance_Performance_by-__legends_iitdhanbad_and__wtc_society__._.__fotofreaks_iitism__iitism__litm__wtc__dance__cultnight__concet_5_v1pnfn.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727112100/IIT_Dhanbad_was_phenomenal._%EF%B8%8F_._Wearing__thesouledstore__._Managed_by__the_hustler_kind__._Amazing_work_behind_the_cameras_by__vaibhav_007___and__fotofreaks_iitism____concetto.iitism__tribevi_2_kbrbw3.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727112096/A_short_glimpse_of_Magic_Show_performed_by__magicboy_kabir_organised_under_Concetto_23._._.__fotofreaks_iitism__iitism__concetto__magicshow__kabirsharma__abracadabra__collegefest__fest_0_JPG_npmzlq.jpg",
            "https://res.cloudinary.com/dimf24hn7/image/upload/v1727112098/Here_are_some_glimpses_of_EDM_night_conducted_under__concetto.iitism_2023._._._Ft.__progressivebrothers____kavyakhurana_._.__edmnight__edm__probros__sunburn__sunburncampus__kavyakhurana__iitism__3_jjwisf.jpg"
        )

        recyclerView.adapter = GalleryAdapter(imageUrls,navController)
        recyclerView.layoutManager = GridLayoutManager(context, 2) // 2 columns
        return view
    }
}
