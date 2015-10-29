package com.tadhg.moodchart.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;
import com.tadhg.moodchart.R;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;

/**
 * Created by Tadhg on 22/09/2015.
 */
public class GraphFragment extends Fragment {

    private XYPlot plot;

    public GraphFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_graph, container, false);



       // plot.getBackgroundPaint().setColor(Color.TRANSPARENT);
       // plot.getGraphWidget().getBackgroundPaint().setColor(Color.TRANSPARENT);
       // plot.getGraphWidget().getGridBackgroundPaint().setColor(Color.TRANSPARENT);

        final String[] days = new String[] {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        Number[] yValues = { 1, 2, 2, 4, 3, 3, 4 };
        Number[] xDays =   { 1  , 2   , 3   , 4   , 5   , 6   , 7 };

        final String[] xLabels = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        class GraphXLabelFormat extends Format {

            @Override
            public StringBuffer format(Object arg0, StringBuffer arg1, FieldPosition arg2) {
                // TODO Auto-generated method stub

                int parsedInt = Math.round(Float.parseFloat(arg0.toString()));
                Log.d("test", parsedInt + " " + arg1 + " " + arg2);
                String labelString = xLabels[parsedInt];
                arg1.append(labelString);
                return arg1;
            }

            @Override
            public Object parseObject(String arg0, ParsePosition arg1) {
                // TODO Auto-generated method stub
                return java.util.Arrays.asList(xLabels).indexOf(arg0);
            }
        }


        // initialize our XYPlot reference:
        plot = (XYPlot) rootView.findViewById(R.id.mySimpleXYPlot);

        plot.getGraphWidget().setDomainValueFormat(new GraphXLabelFormat());

        /*
        // Formatting the Domain Values ( X-Axis )
        plot.setDomainValueFormat(new Format() {

            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                return new StringBuffer( days[ ( (Number)obj).intValue() ]  );
            }

            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;
            }
        });*/
        //Domain

        plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, xDays.length);
        plot.setDomainValueFormat(new DecimalFormat("0"));
        plot.setDomainStepValue(1);

        //Range
        plot.setRangeBoundaries(1, 5, BoundaryMode.FIXED);
        plot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, yValues.length);
        plot.setRangeValueFormat(new DecimalFormat("0"));
        plot.setRangeStepValue(1);



        // Turn the above arrays into XYSeries':
        XYSeries series1 = new SimpleXYSeries(
                Arrays.asList(xDays),          // SimpleXYSeries takes a List so turn our array into a List
                Arrays.asList(yValues),
                "Series1");                             // Set the display title of the series


        // Create a formatter to use for drawing a series using LineAndPointRenderer
        // and configure it from xml:
        LineAndPointFormatter series1Format = new LineAndPointFormatter();
        series1Format.setPointLabelFormatter(new PointLabelFormatter());
        series1Format.configure(getActivity(),
                R.xml.line_point_formatter_with_plf1);

        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format);

        // same as above:
        LineAndPointFormatter series2Format = new LineAndPointFormatter();
        series2Format.setPointLabelFormatter(new PointLabelFormatter());
        series2Format.configure(getActivity(),
                R.xml.line_point_formatter_with_plf2);
       // plot.addSeries(series2, series2Format);

        // reduce the number of range labels
       // plot.setTicksPerRangeLabel(3);

/*
         Number[] days =   { 1  , 2   , 3   , 4   , 5   , 6   , 7 };
    Number[] values = { 380, 1433, 1965, 3200, 3651, 3215, 3217 };

    // initialize our XYPlot reference:
    mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot);

    mySimpleXYPlot.setBorderStyle(Plot.BorderStyle.NONE, null, null);
    mySimpleXYPlot.setPlotMargins(0, 0, 0, 0);
    mySimpleXYPlot.setPlotPadding(0, 0, 0, 0);
    mySimpleXYPlot.setGridPadding(0, 10, 5, 0);

    mySimpleXYPlot.setBackgroundColor(Color.WHITE);

    mySimpleXYPlot.position(
            mySimpleXYPlot.getGraphWidget(),
            0,
            XLayoutStyle.ABSOLUTE_FROM_LEFT,
            0,
            YLayoutStyle.RELATIVE_TO_CENTER,
            AnchorPosition.LEFT_MIDDLE);

    mySimpleXYPlot.getGraphWidget().getBackgroundPaint().setColor(Color.WHITE);
    mySimpleXYPlot.getGraphWidget().getGridBackgroundPaint().setColor(Color.WHITE);

    mySimpleXYPlot.getGraphWidget().getDomainLabelPaint().setColor(Color.BLACK);
    mySimpleXYPlot.getGraphWidget().getRangeLabelPaint().setColor(Color.BLACK);

    mySimpleXYPlot.getGraphWidget().getDomainOriginLabelPaint().setColor(Color.BLACK);
    mySimpleXYPlot.getGraphWidget().getDomainOriginLinePaint().setColor(Color.BLACK);
    mySimpleXYPlot.getGraphWidget().getRangeOriginLinePaint().setColor(Color.BLACK);

    // Domain
    mySimpleXYPlot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, days.length);
    mySimpleXYPlot.setDomainValueFormat(new DecimalFormat("0"));
    mySimpleXYPlot.setDomainStepValue(1);

    //Range
    mySimpleXYPlot.setRangeBoundaries(0, 4500, BoundaryMode.FIXED);
    mySimpleXYPlot.setRangeStepValue(10);
    //mySimpleXYPlot.setRangeStep(XYStepMode.SUBDIVIDE, values.length);
    mySimpleXYPlot.setRangeValueFormat(new DecimalFormat("0"));

    //Remove legend
    mySimpleXYPlot.getLayoutManager().remove(mySimpleXYPlot.getLegendWidget());
    mySimpleXYPlot.getLayoutManager().remove(mySimpleXYPlot.getDomainLabelWidget());
    mySimpleXYPlot.getLayoutManager().remove(mySimpleXYPlot.getRangeLabelWidget());
    mySimpleXYPlot.getLayoutManager().remove(mySimpleXYPlot.getTitleWidget());

    // Turn the above arrays into XYSeries':
    XYSeries series1 = new SimpleXYSeries(
            Arrays.asList(days),
            Arrays.asList(values),
            "Series1");                             // Set the display title of the series

    // Create a formatter to use for drawing a series using LineAndPointRenderer:
    LineAndPointFormatter series1Format = new LineAndPointFormatter(
            Color.rgb(0, 200, 0),                   // line color
            Color.rgb(0, 100, 0),                   // point color
            Color.CYAN);                            // fill color

 // setup our line fill paint to be a slightly transparent gradient:
    Paint lineFill = new Paint();
    lineFill.setAlpha(200);
    lineFill.setShader(new LinearGradient(0, 0, 0, 250, Color.WHITE, Color.GREEN, Shader.TileMode.MIRROR));

    series1Format.setFillPaint(lineFill);

    // add a new series' to the xyplot:
    mySimpleXYPlot.addSeries(series1, series1Format);

    // by default, AndroidPlot displays developer guides to aid in laying out your plot.
    // To get rid of them call disableAllMarkup():
    mySimpleXYPlot.disableAllMarkup();
*/




        // Inflate the layout for this fragment
        return rootView;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

