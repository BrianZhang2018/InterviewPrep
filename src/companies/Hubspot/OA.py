import datetime
from collections import defaultdict

import requests

#https://www.1point3acres.com/bbs/thread-822006-1-1.html

date_format = "%Y-%m-%d"
get_url = "https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=a78c3bcff3b87d327e481683553a"
post_url = "https://candidate.hubteam.com/candidateTest/v3/problem/result?userKey=a78c3bcff3b87d327e481683553a"

def is_consecutive_date(date1, date2):
    date1 = datetime.datetime.strptime(date1, date_format)
    date2 = datetime.datetime.strptime(date2, date_format)
    return (date2 - date1).days == 1

def handler(post_url, get_url):

    try:
        data = requests.get(get_url).json()
        country_partners = defaultdict(list)
        for partner in data["partners"]:
            country = partner["country"]
            country_partners[country].append(partner)

        res = {"countries": []}

        for country in country_partners:
            consecutive_dates_partners = defaultdict(set)
            for partner in country_partners[country]:
                available_dates = partner["availableDates"]
                for i in range(1, len(available_dates)):
                    if is_consecutive_date(available_dates[i - 1], available_dates[i]):
                        consecutive_dates_partners[available_dates[i - 1]].add(partner["email"])

            if len(consecutive_dates_partners) != 0:

                most_attendee_date = max(consecutive_dates_partners, key=lambda k:len(consecutive_dates_partners[k]))
                number_of_attendee = len(consecutive_dates_partners[most_attendee_date])
                # In case of multiple dates with the same number of partners, pick the earlier date.
                all_dates = [k for k in consecutive_dates_partners.keys() if len(consecutive_dates_partners[k]) == number_of_attendee]
                earlier_date = min(all_dates)

                res["countries"].append({
                    "attendeeCount": number_of_attendee,
                    "attendees": list(consecutive_dates_partners[earlier_date]),
                    "name": country,
                    "startDate": earlier_date
                })

                #print(res)
            else:

                res["countries"].append({
                    "attendeeCount": 0,
                    "attendees": [],
                    "name": country,
                    "startDate": None
                })

        #find_overlap(res, dates, country)
        resp = requests.post(post_url, json=res)
        if resp.status_code != 200:
            print(resp.status_code, resp.content)

        print("Success!")

    except Exception as e:
        print("Exception is: " + e)

handler(post_url, get_url)