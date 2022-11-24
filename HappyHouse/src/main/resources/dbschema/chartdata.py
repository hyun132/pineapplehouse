import requests
import json
import pandas as pd
from sqlalchemy import create_engine
import pymysql

db_connection_str = 'mysql+pymysql://ssafy:ssafy@localhost:3306/happyhouse'
db_connection = create_engine(db_connection_str)
conn = db_connection.connect()

def get_sido_info():
    down_url = 'https://new.land.naver.com/api/regions/list?cortarNo=0000000000'
    r = requests.get(down_url,data={"sameAddressGroup":"false"},headers={
        "Accept-Encoding": "gzip",
        "Host": "new.land.naver.com",
        "authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2Njg4NDAwMzksImV4cCI6MTY2ODg1MDgzOX0.vI2xBRbvcuU8z0wr14Bs3iaijdolmy0JsevqDtOcX1k",
        "Referer": "https://new.land.naver.com/complexes/102378?ms=37.5018495,127.0438028,16&a=APT&b=A1&e=RETAIL",
        "Sec-Fetch-Dest": "empty",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Site": "same-origin",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
    })
    r.encoding = "utf-8-sig"
    temp=json.loads(r.text)
    temp=list(pd.DataFrame(temp["regionList"])["cortarNo"])
    return temp
def get_gungu_info(sido_code):
    down_url = 'https://new.land.naver.com/api/regions/list?cortarNo='+sido_code
    r = requests.get(down_url,data={"sameAddressGroup":"false"},headers={
        "Accept-Encoding": "gzip",
        "Host": "new.land.naver.com",
        "authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2Njg4NDAwMzksImV4cCI6MTY2ODg1MDgzOX0.vI2xBRbvcuU8z0wr14Bs3iaijdolmy0JsevqDtOcX1k",
        "Referer": "https://new.land.naver.com/complexes/102378?ms=37.5018495,127.0438028,16&a=APT&b=A1&e=RETAIL",
        "Sec-Fetch-Dest": "empty",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Site": "same-origin",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
    })
    r.encoding = "utf-8-sig"
    temp=json.loads(r.text)
    temp=list(pd.DataFrame(temp['regionList'])["cortarNo"])
    return temp
def get_dong_info(gungu_code):
    down_url = 'https://new.land.naver.com/api/regions/list?cortarNo='+gungu_code
    r = requests.get(down_url,data={"sameAddressGroup":"false"},headers={
        "Accept-Encoding": "gzip",
        "Host": "new.land.naver.com",
        "authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2Njg4NDAwMzksImV4cCI6MTY2ODg1MDgzOX0.vI2xBRbvcuU8z0wr14Bs3iaijdolmy0JsevqDtOcX1k",
        "Referer": "https://new.land.naver.com/complexes/102378?ms=37.5018495,127.0438028,16&a=APT&b=A1&e=RETAIL",
        "Sec-Fetch-Dest": "empty",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Site": "same-origin",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
    })
    r.encoding = "utf-8-sig"
    temp=json.loads(r.text)
    temp=list(pd.DataFrame(temp['regionList'])["cortarNo"])
    return temp
def get_apt_list(dong_code):
    down_url = 'https://new.land.naver.com/api/regions/complexes?cortarNo='+dong_code+'&realEstateType=APT&order='
    r = requests.get(down_url,data={"sameAddressGroup":"false"},headers={
        "Accept-Encoding": "gzip",
        "authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2Njg4NDAwMzksImV4cCI6MTY2ODg1MDgzOX0.vI2xBRbvcuU8z0wr14Bs3iaijdolmy0JsevqDtOcX1k",
        "Host": "new.land.naver.com",
        "Referer": "https://new.land.naver.com/complexes/102378?ms=37.5018495,127.0438028,16&a=APT&b=A1&e=RETAIL",
        "Sec-Fetch-Dest": "empty",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Site": "same-origin",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
    })
    r.encoding = "utf-8-sig"
    temp=json.loads(r.text)
    try:
        temp=list(pd.DataFrame(temp['complexList'])["complexNo"])
    except:
        temp=[]
    return temp


def get_apt_info(apt_code):
    down_url = 'https://new.land.naver.com/api/complexes/'+apt_code+'?sameAddressGroup=false'
    r = requests.get(down_url,data={"sameAddressGroup":"false"},headers={
        "Accept-Encoding": "gzip",
        "Host": "new.land.naver.com",
        "authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2Njg4NDAwMzksImV4cCI6MTY2ODg1MDgzOX0.vI2xBRbvcuU8z0wr14Bs3iaijdolmy0JsevqDtOcX1k",
        "Referer": "https://new.land.naver.com/complexes/"+apt_code+"?ms=37.482968,127.0634,16&a=APT&b=A1&e=RETAIL",
        "Sec-Fetch-Dest": "empty",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Site": "same-origin",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
    })
    r.encoding = "utf-8-sig"
    temp=json.loads(r.text)
    return temp

def get_school_info(apt_code):
    down_url = 'https://new.land.naver.com/api/complexes/'+apt_code+'/schools'
    r = requests.get(down_url,headers={
        "Accept-Encoding": "gzip",
        "Host": "new.land.naver.com",
        "authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2Njg4NDAwMzksImV4cCI6MTY2ODg1MDgzOX0.vI2xBRbvcuU8z0wr14Bs3iaijdolmy0JsevqDtOcX1k",
        "Referer": "https://new.land.naver.com/complexes/"+apt_code+"?ms=37.482968,127.0634,16&a=APT&b=A1&e=RETAIL",
        "Sec-Fetch-Dest": "empty",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Site": "same-origin",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
    })
    r.encoding = "utf-8-sig"
    temp_school=json.loads(r.text)
    return temp_school
##################가격정보
def apt_price(apt_code,index):
    p_num=temp["complexPyeongDetailList"][index]["pyeongNo"]
    down_url = 'https://new.land.naver.com/api/complexes/'+apt_code+'/prices?complexNo='+apt_code+'&tradeType=A1&year=5&priceChartChange=true&areaNo='+p_num+'&areaChange=true&type=table'

    r = requests.get(down_url,headers={
        "Accept-Encoding": "gzip",
        "Host": "new.land.naver.com",
        "authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2Njg4NDAwMzksImV4cCI6MTY2ODg1MDgzOX0.vI2xBRbvcuU8z0wr14Bs3iaijdolmy0JsevqDtOcX1k",
        "Referer": "https://new.land.naver.com/complexes/"+apt_code+"?ms=37.4830877,127.0579863,15&a=APT&b=A1&e=RETAIL",
        "Sec-Fetch-Dest": "empty",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Site": "same-origin",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
    })
    r.encoding = "utf-8-sig"
    temp_price=json.loads(r.text)
    return temp_price

def apt_price_chart(apt_code,index):
    p_num=temp["complexPyeongDetailList"][index]["pyeongNo"]
    down_url = 'https://new.land.naver.com/api/complexes/'+apt_code+'/prices?complexNo='+apt_code+'&tradeType=A1&year=5&areaNo='+p_num+'&type=chart'

    r = requests.get(down_url,headers={
        "Accept-Encoding": "gzip",
        "Host": "new.land.naver.com",
        "authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2Njg4NDAwMzksImV4cCI6MTY2ODg1MDgzOX0.vI2xBRbvcuU8z0wr14Bs3iaijdolmy0JsevqDtOcX1k",
        "Referer": "https://new.land.naver.com/complexes/"+apt_code+"?ms=37.4830877,127.0579863,15&a=APT&b=A1&e=RETAIL",
        "Sec-Fetch-Dest": "empty",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Site": "same-origin",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
    })
    r.encoding = "utf-8-sig"
    temp_price=json.loads(r.text)
    return temp_price


sido_list=get_sido_info()
for m in range(len(sido_list)):
    gungu_list=get_gungu_info(sido_list[m])
    gungu_apt_list=[0]*len(gungu_list)
    # for j in range(1):#구 마다 하나씩 저장
    for j in range(len(gungu_list)):#구 마다 하나씩 저장
        dong_list=get_dong_info(gungu_list[j])
        dong_apt_list=[0]*len(dong_list)
        for k in range(5,len(dong_list)):#동마다 하나씩 저장
        # for k in range(1,3):#동마다 하나씩 저장
            apt_list=get_apt_list(dong_list[k])
            apt_list_data=[0]*len(apt_list)
            # for n in range(10):#아파트 마다 하나씩 저장
            for n in range(1,len(apt_list),30):#아파트 마다 하나씩 저장
                temp=get_apt_info(apt_list[n])

                try:
                    area_list=temp["complexDetail"]["pyoengNames"].split(", ")
                    ex_flag=1
                except KeyError:
                    ex_flag=0
                    temp_data=pd.DataFrame(columns=temp_data.columns)
                if ex_flag==1:

                    temp_data = pd.DataFrame(index=range(len(area_list)))
                for i in range(len(area_list)):
                    temp_price = apt_price_chart(apt_list[0], i)
                    print(temp_price)
                    keys=[temp["complexDetail"]["complexNo"]]*len(temp_price["marketPriceXList"])
                    try:
                        obj = {
                            'aptCode': temp["complexDetail"]["complexNo"],
                            'marketPrice': temp_price["marketPriceXList"][1::5],
                            'upperMarketPrice': temp_price["upperMarketPriceYList"][1::5],
                            'lowerMarketPrice': temp_price["lowerMarketPriceYList"][1::5],
                            'ChartBasisDate': temp_price["marketPriceChartBasisYearMonthDay"][1::5]
                        }
                        print(temp["complexDetail"]["complexNo"])
                        pd.DataFrame(obj).to_sql(name='chartdatas', con=db_connection, if_exists='append', index=False)
                    except KeyError:
                        print("skip")
                    # copied = temp_data[:]
                    # copied.to_sql(name='test11201', con=db_connection, if_exists='append', index=False)



            # if apt_list_data==[] and temp_data is not None:
            #     dong_apt_list[k]=pd.DataFrame(columns=temp_data.columns)
            # else:
            #     dong_apt_list[k]=pd.concat(apt_list_data)
    #
    #     gungu_apt_list[j]=pd.concat(dong_apt_list)
    #     gungu_apt_list[j].to_csv(temp["complexDetail"]["roadAddressPrefix"]+".csv",encoding="CP949")
    #     # gungu_apt_list[j].to_sql(name='aptData', con=db_connection, if_exists='append', index=False)
    # final_data=pd.concat(gungu_apt_list)
    # # copied = final_data[:]
    # # copied.to_sql(name='aparttest', con=db_connection, if_exists='append', index=False)
    # final_data.to_csv(temp["complexDetail"]["roadAddressPrefix"].split()[0]+".csv",encoding="CP949")
    # final_data.to_sql(name='aptData', con=db_connection, if_exists='append', index=False)
    conn.close()

{
   "dongCode ",    "aptName ",    "pyoeng" ,  "address ",
   "roadAddress ",
   "latitude ",
   "longitude ",
   "totalHouseholdCount ",
   "totalLeaseHouseholdCount ",
   "highFloor ",
   "lowFloor ",
   "representativePhoto ",
   "aptCode ",
   "batlRatio ",
   "btlRatio ",
   "parkingPossibleCount ",
   "constructionCompanyName ",
   "heatMethodTypeCode ",
   "supplyArea ",
   "exclusiveArea ",
   "exclusiveRate ",
   "roomCnt ",
   "bathroomCnt ",
   "householdCountByPyeong ",
   "dealPrice ",
   "leasePrice ",
   "rentPrice"}