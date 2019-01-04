package in.barbool.famousplaces.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.barbool.famousplaces.Models.BookHotelModel;
import in.barbool.famousplaces.R;

public class BookHotelAdapter extends RecyclerView.Adapter<BookHotelAdapter.ViewHolder> {
    Context context;
    ArrayList<BookHotelModel> bookHotelModels;

    public BookHotelAdapter(Context context, ArrayList<BookHotelModel> bookHotelModels) {
        this.context = context;
        this.bookHotelModels = bookHotelModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.web_visit_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.website.setText(bookHotelModels.get(i).getBookWebsite());
        viewHolder.price.setText(bookHotelModels.get(i).getBookPrice());
        Picasso.get().load(bookHotelModels.get(i).getBookImage()).into(viewHolder.imageView);

        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String url = "http://"+ bookHotelModels.get(i).getBookWebAddress();
               Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookHotelModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView website, price;
        ImageView imageView;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            website = (TextView) itemView.findViewById(R.id.textView4);
            price = (TextView) itemView.findViewById(R.id.textView3);
            imageView = (ImageView) itemView.findViewById(R.id.imageView3);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.webClick);


        }
    }
}
