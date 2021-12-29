package ma.ensa.drugstore.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ma.ensa.drugstore.R;

import java.util.ArrayList;

import ma.ensa.drugstore.adapter.InactiveMedsAdapter;
import ma.ensa.drugstore.adapter.MedsAdapter;
import ma.ensa.drugstore.database.Database;
import de.hdodenhof.circleimageview.CircleImageView;
import ma.ensa.drugstore.model.MedsData;

public class MedsFragment extends Fragment {

    RecyclerView recyclerView, inActiveRecyclerView;
    MedsAdapter adapter;
    InactiveMedsAdapter inactiveMedsAdapter;
    RecyclerView.LayoutManager layoutManager, inActiveLayoutManager;

    private ArrayList<String> medNameData = new ArrayList<String>();
    private ArrayList<Integer> medPhotoData = new ArrayList<Integer>();
    private ArrayList<String> inActiveMedNameData = new ArrayList<String>();
    private ArrayList<Integer> inActiveMedPhotoData = new ArrayList<Integer>();
    private Database db;

    TextView tvMedName;
    CircleImageView imgMed;
    Toolbar toolbarMeds;
    CardView inActiveCard;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pillbox, null);
        tvMedName = view.findViewById(R.id.txtMedName);
        imgMed = view.findViewById(R.id.imgMed);
        toolbarMeds = view.findViewById(R.id.toolbarMedbox);
        inActiveCard = view.findViewById(R.id.card_MedboxInactive);

        initMedsList();
        recyclerView = view.findViewById(R.id.recycler_view_Medbox);
        adapter = new MedsAdapter(this.getContext(), medPhotoData, medNameData);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        initInactiveMedsList();
        if(inActiveMedNameData.isEmpty())
            inActiveCard.setVisibility(View.GONE);
        inActiveRecyclerView = view.findViewById(R.id.recycler_view_MedboxInactive);
        inactiveMedsAdapter = new InactiveMedsAdapter(this.getContext(), inActiveMedPhotoData, inActiveMedNameData);
        inActiveRecyclerView.setAdapter(inactiveMedsAdapter);
        inActiveLayoutManager = new LinearLayoutManager(getActivity());
        inActiveRecyclerView.setLayoutManager(inActiveLayoutManager);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        toolbarMeds.setTitle(R.string.title_medbox);
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    private void initMedsList() {
        try {
            db = new Database(getContext());
            ArrayList<MedsData> medsList = db.getActivePills();
            for (MedsData med : medsList) {
                medNameData.add(med.getPillName());
                medPhotoData.add(med.getPhotoId());
            }
            db.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void initInactiveMedsList() {
        try {
            db = new Database(getContext());
            ArrayList<MedsData> medsList = db.getInActivePills();
            for (MedsData med : medsList) {
                inActiveMedNameData.add(med.getPillName());
                inActiveMedPhotoData.add(med.getPhotoId());
            }
            db.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
